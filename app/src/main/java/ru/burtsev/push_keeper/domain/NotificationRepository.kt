package ru.burtsev.push_keeper.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.burtsev.push_keeper.data.db.AppDatabase
import ru.burtsev.push_keeper.data.db.app.AppEntity
import ru.burtsev.push_keeper.data.db.app.AppInfoMapper
import ru.burtsev.push_keeper.data.db.notification.NotificationEntity
import ru.burtsev.push_keeper.data.db.notification.NotificationMapper
import ru.burtsev.push_keeper.domain.model.app.AppInfo
import ru.burtsev.push_keeper.domain.model.notification.Notification

interface NotificationRepository {
    fun getNotifications(): Flow<List<Notification>>

    suspend fun getApps(): Set<AppInfo>

    suspend fun insertNotifications(entity: NotificationEntity): Long

    suspend fun insertApp(appInfo: AppInfo): Long
}

class NotificationRepositoryImpl(database: AppDatabase) : NotificationRepository {

    private val notificationDao = database.getNotificationDao()
    private val appDao = database.getAppDao()

    override fun getNotifications(): Flow<List<Notification>> {
        return notificationDao.getNotifications().map {
            NotificationMapper.mapToDomain(it)
        }
    }

    override suspend fun getApps(): Set<AppInfo> {
        val apps = appDao.getApps()
        return AppInfoMapper.mapToDomain(apps)
    }

    override suspend fun insertNotifications(entity: NotificationEntity): Long {
        return notificationDao.insertNotification(entity)
    }

    override suspend fun insertApp(appInfo: AppInfo): Long {
        val entity = AppEntity(
            id = 0,
            packages = appInfo.packages,
            appName = appInfo.appName,
            isEnabled = appInfo.isEnabled,
        )
        return appDao.insertApp(entity)
    }


}
