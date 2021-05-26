package ru.burtsev.push_keeper.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.domain.NotificationInteractor

class MainViewModel(private val notificationInteractor: NotificationInteractor) : ViewModel() {

    var notificationLiveData: LiveData<List<NotificationEntity>>? = null


    init {
        notificationLiveData = notificationInteractor.getNotifications().asLiveData(Dispatchers.Main)
    }
}