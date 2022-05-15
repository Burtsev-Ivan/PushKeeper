package ru.burtsev.push_keeper.presentation.screens.notifications

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.burtsev.push_keeper.domain.NotificationInteractor
import ru.burtsev.push_keeper.domain.model.notification.Notification

class NotificationsViewModel(notificationInteractor: NotificationInteractor) : ViewModel() {

    val notificationFlow: Flow<PagingData<Notification>> =
        notificationInteractor.getNotifications()

}
