package ru.burtsev.push_keeper.data.db.notification

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notificationEntity: NotificationEntity): Long


    @Query("Select * from Notification ORDER BY id DESC")
    fun getNotifications(): Flow<List<NotificationEntity>>

}
