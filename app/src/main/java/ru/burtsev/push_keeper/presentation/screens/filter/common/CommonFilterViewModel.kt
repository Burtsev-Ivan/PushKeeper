package ru.burtsev.push_keeper.presentation.screens.filter.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.burtsev.push_keeper.domain.NotificationInteractor

class CommonFilterViewModel(private val notificationInteractor: NotificationInteractor) :
    ViewModel() {

    private val _filterStateLiveData = MutableLiveData(FilterAppViewState())
    val filterStateLiveData: LiveData<FilterAppViewState> get() = _filterStateLiveData

//    init {
//        viewModelScope.launch {
//            val apps = notificationInteractor.getApps()
//            val isCheckedAll = apps.all { it.isEnabled }
//            val viewState = _appInfoLiveData.value?.copy(
//                isLoading = false,
//                apps = apps,
//                isCheckedAll = isCheckedAll,
//            )
//            _appInfoLiveData.postValue(viewState)
//        }
//    }
//
//    // Изменяем значение isEnabled у всех элементов
//    fun onCheckedAll() {
//        val apps = _appInfoLiveData.value?.apps ?: return
//        if (apps.all { it.isEnabled }) {
//            val appAllEnabled = apps.map { it.copy(isEnabled = false) }
//            val viewState = _appInfoLiveData.value?.copy(
//                apps = appAllEnabled,
//                isCheckedAll = false,
//            )
//            _appInfoLiveData.postValue(viewState)
//        } else {
//            val appAllEnabled = apps.map { it.copy(isEnabled = true) }
//            val viewState = _appInfoLiveData.value?.copy(
//                apps = appAllEnabled,
//                isCheckedAll = true,
//            )
//            _appInfoLiveData.postValue(viewState)
//        }
//    }
//
//    // Изменяем значение isEnabled у нажатого элемента AppInfo (Удаляем старый и
//    // добавляем новый элемент с измененным значением )
//    fun onCheckedChange(appInfo: AppInfo) {
//        val apps = _appInfoLiveData.value?.apps ?: return
//        apps.indexOfFirst { it.packages == appInfo.packages }.let { index ->
//            if (index == -1) return@let
//            val mutableApps = apps.toMutableList().apply { removeAt(index) }
//            val newAppInfo = apps[index].copy(isEnabled = !apps[index].isEnabled)
//            mutableApps.add(index, newAppInfo)
//            val isCheckedAll = mutableApps.all { it.isEnabled }
//            val viewState =
//                _appInfoLiveData.value?.copy(apps = mutableApps, isCheckedAll = isCheckedAll)
//            _appInfoLiveData.postValue(viewState)
//        }
//    }
//
//    fun onSaveClick() {
//        val apps = _appInfoLiveData.value?.apps ?: return
//        viewModelScope.launch {
//            notificationInteractor.saveFilterAppInfo(apps)
//            val viewState = _appInfoLiveData.value?.copy(
//                event = Event.GoBack,
//            )
//            _appInfoLiveData.postValue(viewState)
//        }
//
//    }

    fun onTextChanged(text: String) {
        val autocompleteState = filterStateLiveData.value?.autocompleteState ?: return

        val filteredItems = autocompleteState.values.filter {
            it.text.contains(text, true)
        }

        _filterStateLiveData.value = filterStateLiveData.value?.copy(
            autocompleteState = autocompleteState.copy(
                text = text,
                filteredItems = filteredItems,
                isSearching = true,
            )
        )
    }

    fun onFocusChanged(isFocused: Boolean) {
        val autocompleteState =
            filterStateLiveData.value?.autocompleteState?.copy(isSearching = isFocused) ?: return

        _filterStateLiveData.value =
            filterStateLiveData.value?.copy(autocompleteState = autocompleteState)
    }

    fun onItemClick(item: AutocompleteItem<String>) {
        val autocompleteState = filterStateLiveData.value?.autocompleteState?.copy(
            text = item.text,
            isSearching = false,
        ) ?: return

        _filterStateLiveData.value =
            filterStateLiveData.value?.copy(autocompleteState = autocompleteState)
    }

}
