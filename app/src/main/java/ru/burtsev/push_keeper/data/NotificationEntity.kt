package ru.burtsev.push_keeper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notification")
class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val packages: String,
    val appName: String,
    val title: String,
    val text: String
)