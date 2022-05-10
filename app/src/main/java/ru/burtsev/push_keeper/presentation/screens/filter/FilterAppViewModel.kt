package ru.burtsev.push_keeper.presentation.screens.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.burtsev.push_keeper.domain.NotificationInteractor
import ru.burtsev.push_keeper.domain.model.app.AppInfo

class FilterAppViewModel(private val notificationInteractor: NotificationInteractor) : ViewModel() {

    private val _appInfoLiveData = MutableLiveData(FilterAppViewState())
    val appInfoLiveData: LiveData<FilterAppViewState> get() = _appInfoLiveData

    init {
        viewModelScope.launch {
            val apps = notificationInteractor.getApps()
            val isCheckedAll = apps.all { it.isEnabled }
            val viewState = _appInfoLiveData.value?.copy(
                isLoading = false,
                apps = apps,
                isCheckedAll = isCheckedAll,
            )
            _appInfoLiveData.postValue(viewState)
        }
    }

    // Изменяем значение isEnabled у всех элементов
    fun onCheckedAll() {
        val apps = _appInfoLiveData.value?.apps ?: return
        if (apps.all { it.isEnabled }) {
            val appAllEnabled = apps.map { it.copy(isEnabled = false) }
            val viewState = _appInfoLiveData.value?.copy(
                apps = appAllEnabled,
                isCheckedAll = false,
            )
            _appInfoLiveData.postValue(viewState)
        } else {
            val appAllEnabled = apps.map { it.copy(isEnabled = true) }
            val viewState = _appInfoLiveData.value?.copy(
                apps = appAllEnabled,
                isCheckedAll = true,
            )
            _appInfoLiveData.postValue(viewState)
        }
    }

    // Изменяем значение isEnabled у нажатого элемента AppInfo (Удаляем старый и
    // добавляем новый элемент с измененным значением )
    fun onCheckedChange(appInfo: AppInfo) {
        val apps = _appInfoLiveData.value?.apps ?: return
        apps.indexOfFirst { it.packages == appInfo.packages }.let { index ->
            if (index == -1) return@let
            val mutableApps = apps.toMutableList().apply { removeAt(index) }
            val newAppInfo = apps[index].copy(isEnabled = !apps[index].isEnabled)
            mutableApps.add(index, newAppInfo)
            val isCheckedAll = mutableApps.all { it.isEnabled }
            val viewState =
                _appInfoLiveData.value?.copy(apps = mutableApps, isCheckedAll = isCheckedAll)
            _appInfoLiveData.postValue(viewState)
        }
    }

    fun onSaveClick() {
        val apps = _appInfoLiveData.value?.apps ?: return
        viewModelScope.launch {
            notificationInteractor.saveFilterAppInfo(apps)
            val viewState = _appInfoLiveData.value?.copy(
                event = Event.GoBack,
            )
            _appInfoLiveData.postValue(viewState)
        }

    }

}
