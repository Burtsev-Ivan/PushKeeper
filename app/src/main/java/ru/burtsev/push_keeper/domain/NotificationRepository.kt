package ru.burtsev.push_keeper.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import java.util.concurrent.Executors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.burtsev.push_keeper.data.db.AppDatabase
import ru.burtsev.push_keeper.data.db.app.AppEntity
import ru.burtsev.push_keeper.data.db.app.AppInfoMapper
import ru.burtsev.push_keeper.data.db.notification.NotificationEntity
import ru.burtsev.push_keeper.data.db.notification.NotificationMapper
import ru.burtsev.push_keeper.domain.model.app.AppInfo
import ru.burtsev.push_keeper.domain.model.notification.Notification

interface NotificationRepository {
    fun getNotifications(): Flow<PagingData<Notification>>

    suspend fun getApps(): List<AppInfo>

    suspend fun insertNotifications(entity: NotificationEntity): Long

    suspend fun insertApp(appInfo: AppInfo): Long

    suspend fun updateApp(appInfo: AppInfo)
}

private const val PAGE_SIZE = 15
private const val DEFAULT_THREAD_POOL_SIZE = 4


class NotificationRepositoryImpl(database: AppDatabase) : NotificationRepository {

    private val mutex = Mutex()

    private val notificationDao = database.getNotificationDao()
    private val appDao = database.getAppDao()

    override fun getNotifications(): Flow<PagingData<Notification>> {
        val pagingSourceFactory = { notificationDao.getNotifications() }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
            .map { pagingData: PagingData<NotificationEntity> ->
                pagingData.map(
                    executor = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE),
                    transform = { notificationEntity ->
                        NotificationMapper.mapToDomain(notificationEntity)
                    },
                )
            }
    }

    override suspend fun getApps(): List<AppInfo> {
        val apps = appDao.getApps()
        return AppInfoMapper.mapToDomain(apps)
    }

    override suspend fun insertNotifications(entity: NotificationEntity): Long {
        return notificationDao.insertNotification(entity)
    }

    override suspend fun insertApp(appInfo: AppInfo): Long {
        mutex.withLock {
            appDao.getAppByPackages(appInfo.packages)?.let { return it.id }
            val entity = AppEntity(
                id = 0,
                packages = appInfo.packages,
                appName = appInfo.appName,
                isEnabled = appInfo.isEnabled,
            )
            return appDao.insertApp(entity)
        }
    }

    override suspend fun updateApp(appInfo: AppInfo) {
        appDao.updateIsEnableFlag(appInfo.id, appInfo.isEnabled)
    }

}
