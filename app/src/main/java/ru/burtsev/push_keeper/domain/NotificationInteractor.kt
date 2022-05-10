package ru.burtsev.push_keeper.domain

import kotlinx.coroutines.flow.Flow
import ru.burtsev.push_keeper.data.db.notification.NotificationEntity
import ru.burtsev.push_keeper.domain.model.app.AppInfo
import ru.burtsev.push_keeper.domain.model.notification.Notification

class NotificationInteractor(private val notificationRepository: NotificationRepository) {

    fun getNotifications(): Flow<List<Notification>> {
        return notificationRepository.getNotifications()
    }

    suspend fun getApps(): Set<AppInfo> {
        return notificationRepository.getApps()
    }

    suspend fun insertNotifications(entity: NotificationEntity): Long {
        val notificationId = notificationRepository.insertNotifications(entity)
        val apps = notificationRepository.getApps()
        if (apps.all { it.packages != entity.packages }) {
            notificationRepository.insertApp(
                AppInfo(
                    packages = entity.packages,
                    appName = entity.appName,
                    isEnabled = true
                )
            )
        }
        return notificationId
    }
}
