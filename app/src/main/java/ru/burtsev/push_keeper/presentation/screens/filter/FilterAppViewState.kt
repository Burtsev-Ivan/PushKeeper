package ru.burtsev.push_keeper.presentation.screens.filter

import ru.burtsev.push_keeper.domain.model.app.AppInfo

data class FilterAppViewState(
    val isLoading: Boolean = true,
    val apps: Set<AppInfo> = mutableSetOf()
)
