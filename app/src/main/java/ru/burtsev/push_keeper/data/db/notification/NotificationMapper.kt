package ru.burtsev.push_keeper.data.db.notification

import java.text.SimpleDateFormat
import ru.burtsev.push_keeper.domain.model.notification.Notification

object NotificationMapper {
    private val formatter = SimpleDateFormat("dd.MM HH:mm:ss")

    fun mapToDomain(notificationEntity: NotificationEntity): Notification {
        return Notification(
            id = notificationEntity.id,
            packages = notificationEntity.packages,
            appName = notificationEntity.appName,
            title = notificationEntity.title,
            text = notificationEntity.text,
            time = formatter.format(notificationEntity.time)
        )

    }
}
