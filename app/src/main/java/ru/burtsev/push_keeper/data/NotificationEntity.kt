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
    val text: String,
    val time: Long
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NotificationEntity) return false

        if (packages != other.packages) return false
        if (appName != other.appName) return false
        if (title != other.title) return false
        if (text != other.text) return false
        if (time != other.time) return false

        return true
    }

    override fun hashCode(): Int {
        var result = packages.hashCode()
        result = 31 * result + appName.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + time.hashCode()
        return result
    }
}