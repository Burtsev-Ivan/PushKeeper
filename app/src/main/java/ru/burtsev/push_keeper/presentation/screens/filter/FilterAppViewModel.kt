package ru.burtsev.push_keeper.presentation.screens.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.burtsev.push_keeper.domain.NotificationInteractor

class FilterAppViewModel(private val notificationInteractor: NotificationInteractor) : ViewModel() {

    private val _appInfoLiveData = MutableLiveData(FilterAppViewState())
    val appInfoLiveData: LiveData<FilterAppViewState> get() = _appInfoLiveData

    init {
        viewModelScope.launch {
            val apps = notificationInteractor.getApps()
            val viewState = _appInfoLiveData.value?.copy(
                isLoading = false,
                apps = apps
            )
            _appInfoLiveData.postValue(viewState)
        }
    }

}
