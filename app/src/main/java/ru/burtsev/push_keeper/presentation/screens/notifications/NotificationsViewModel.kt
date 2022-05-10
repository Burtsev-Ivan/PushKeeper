package ru.burtsev.push_keeper.presentation.screens.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.burtsev.push_keeper.domain.NotificationInteractor
import ru.burtsev.push_keeper.domain.model.Notification

class NotificationsViewModel(notificationInteractor: NotificationInteractor) : ViewModel() {

    val notificationLiveData: LiveData<List<Notification>> =
        notificationInteractor.getNotifications().asLiveData(Dispatchers.Main)

}
