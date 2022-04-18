package ru.burtsev.push_keeper.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.domain.NotificationInteractor

class MainViewModel(notificationInteractor: NotificationInteractor) : ViewModel() {

    val notificationLiveData: LiveData<List<NotificationEntity>> =
        notificationInteractor.getNotifications().asLiveData(Dispatchers.Main)

}
