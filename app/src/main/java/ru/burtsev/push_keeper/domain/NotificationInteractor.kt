package ru.burtsev.push_keeper.domain

import kotlinx.coroutines.flow.Flow
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.domain.model.Notification

class NotificationInteractor(private val notificationRepository: NotificationRepository) {

    fun getNotifications(): Flow<List<Notification>> {
        return notificationRepository.getNotifications()
    }

    suspend fun insertNotifications(entity: NotificationEntity): Long {
        return notificationRepository.insertNotifications(entity)
    }
}
