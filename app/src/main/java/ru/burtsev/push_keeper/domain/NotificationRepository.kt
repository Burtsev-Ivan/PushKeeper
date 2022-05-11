package ru.burtsev.push_keeper.domain

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
    fun getNotifications(): Flow<List<Notification>>

    suspend fun getApps(): List<AppInfo>

    suspend fun insertNotifications(entity: NotificationEntity): Long

    suspend fun insertApp(appInfo: AppInfo): Long

    suspend fun updateApp(appInfo: AppInfo)
}

class NotificationRepositoryImpl(database: AppDatabase) : NotificationRepository {

    private val mutex = Mutex()

    private val notificationDao = database.getNotificationDao()
    private val appDao = database.getAppDao()

    override fun getNotifications(): Flow<List<Notification>> {
        return notificationDao.getNotifications().map {
            NotificationMapper.mapToDomain(it)
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
