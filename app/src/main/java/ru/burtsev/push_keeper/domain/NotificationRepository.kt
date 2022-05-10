package ru.burtsev.push_keeper.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.burtsev.push_keeper.data.AppDatabase
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.domain.model.Notification
import ru.burtsev.push_keeper.domain.model.NotificationMapper

interface NotificationRepository {
    fun getNotifications(): Flow<List<Notification>>

    suspend fun insertNotifications(entity: NotificationEntity): Long
}

class NotificationRepositoryImpl(database: AppDatabase) : NotificationRepository {

    private val dao = database.getNotificationDao()

    override fun getNotifications(): Flow<List<Notification>> {
        return dao.getNotifications().map {
            NotificationMapper.mapToDomain(it)
        }
    }

    override suspend fun insertNotifications(entity: NotificationEntity): Long {
        return dao.insertNotification(entity)
    }


}
