package ru.burtsev.push_keeper.data.db.notification

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notificationEntity: NotificationEntity): Long


    @Query("Select Notification.* from Notification, Application Where Notification.packages=Application.packages And Application.isEnabled=1  ORDER BY id DESC")
    fun getNotifications(): PagingSource<Int, NotificationEntity>

}
