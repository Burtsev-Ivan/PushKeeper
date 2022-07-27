package ru.burtsev.push_keeper.presentation.screens.filter.common

data class FilterAppViewState(
    val isLoading: Boolean = true,
//    val apps: List<AppInfo> = emptyList(),
//    val isCheckedAll: Boolean = true,
    val isCheckedAll: Boolean = true,
    val autocompleteState: AutocompleteState<String> = AutocompleteState(
        text = "",
        values = prepareAutocompleteList(),
        filteredItems = prepareAutocompleteList(),
        isSearching = false,
    ),
    val event: Event? = null,
)

sealed class Event {
    object GoBack : Event()
}


data class AutocompleteItem<T>(val text: String, val value: T)

data class AutocompleteState<T>(
    val text: String,
    val values: List<AutocompleteItem<T>>,
    val filteredItems: List<AutocompleteItem<T>>,
//    val selectedValues: List<AutocompleteItem<T>>,
    val isSearching: Boolean,
)

private fun prepareAutocompleteList() = listOf(
    AutocompleteItem("1", "1"),
    AutocompleteItem("123", "1"),
    AutocompleteItem("222", "2"),
    AutocompleteItem("187", "3"),
    AutocompleteItem("4", "4"),
    AutocompleteItem("5", "4"),
    AutocompleteItem("Иван", "Иван"),
    AutocompleteItem("Илья", "Иван"),
    AutocompleteItem("Игоша", "Иван"),
)

