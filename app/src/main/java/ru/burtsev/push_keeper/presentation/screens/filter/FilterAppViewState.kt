package ru.burtsev.push_keeper.presentation.screens.filter

import ru.burtsev.push_keeper.domain.model.app.AppInfo

data class FilterAppViewState(
    val isLoading: Boolean = true,
    val apps: List<AppInfo> = emptyList(),
    val isCheckedAll: Boolean = true,
    val event: Event? = null,
)

sealed class Event {
    object GoBack : Event()
}
