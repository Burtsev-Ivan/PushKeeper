package ru.burtsev.push_keeper.domain.model.app

data class AppInfo(
    val id: Long,
    val packages: String,
    val appName: String,
    val isEnabled: Boolean,
)
