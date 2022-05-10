package ru.burtsev.push_keeper.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.domain.model.app.AppInfo
import ru.burtsev.push_keeper.domain.model.notification.Notification

class NotificationInteractor(private val notificationRepository: NotificationRepository) {

    fun getNotifications(): Flow<List<Notification>> {
        return notificationRepository.getNotifications()
    }

    suspend fun getApps(): Set<AppInfo> {
        return notificationRepository.getNotifications().first()
            .mapTo(
                destination = LinkedHashSet(),
                transform = {
                    AppInfo(
                        packages = it.packages,
                        appName = it.appName,
                        isEnabled = true,
                    )
                }
            )
    }

    suspend fun insertNotifications(entity: NotificationEntity): Long {
        return notificationRepository.insertNotifications(entity)
    }
}
