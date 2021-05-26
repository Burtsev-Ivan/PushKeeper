package ru.burtsev.push_keeper.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NotificationEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotificationDao(): NotificationDao

    companion object {
        private const val DATABASE_NAME = "Notification.db"

        fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }


}