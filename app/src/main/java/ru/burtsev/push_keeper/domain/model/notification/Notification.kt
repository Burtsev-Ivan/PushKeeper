package ru.burtsev.push_keeper.domain.model.notification

data class Notification(
    val id: Long,
    val packages: String,
    val appName: String,
    val title: String,
    val text: String,
    val time: String,
)
