package ru.burtsev.push_keeper.presentation.screens.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.burtsev.push_keeper.data.NotificationEntity
import ru.burtsev.push_keeper.domain.NotificationInteractor

class NotificationsViewModel(notificationInteractor: NotificationInteractor) : ViewModel() {

    val notificationLiveData: LiveData<List<NotificationEntity>> =
        notificationInteractor.getNotifications().asLiveData(Dispatchers.Main)

}
