package ru.burtsev.push_keeper.domain

import kotlinx.coroutines.flow.Flow
import ru.burtsev.push_keeper.data.AppDatabase
import ru.burtsev.push_keeper.data.NotificationEntity

interface NotificationRepository {
    fun getNotifications(): Flow<List<NotificationEntity>>

    suspend fun insertNotifications(entity: NotificationEntity): Long
}

class NotificationRepositoryImpl(database: AppDatabase) : NotificationRepository {

    private val dao = database.getNotificationDao()

    override fun getNotifications(): Flow<List<NotificationEntity>> {
        return dao.getNotifications()
    }

    override suspend fun insertNotifications(entity: NotificationEntity): Long {
        return dao.insertNotification(entity)
    }


}