package ru.burtsev.push_keeper.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.burtsev.push_keeper.data.db.app.AppDao
import ru.burtsev.push_keeper.data.db.app.AppEntity
import ru.burtsev.push_keeper.data.db.notification.NotificationDao
import ru.burtsev.push_keeper.data.db.notification.NotificationEntity

@Database(
    entities = [NotificationEntity::class, AppEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotificationDao(): NotificationDao

    abstract fun getAppDao(): AppDao

    companion object {
        private const val DATABASE_NAME = "Notification.db"

        fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }


}
