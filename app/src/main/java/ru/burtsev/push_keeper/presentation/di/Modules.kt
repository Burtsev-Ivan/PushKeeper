package ru.burtsev.push_keeper.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.burtsev.push_keeper.data.db.AppDatabase
import ru.burtsev.push_keeper.domain.NotificationInteractor
import ru.burtsev.push_keeper.domain.NotificationRepository
import ru.burtsev.push_keeper.domain.NotificationRepositoryImpl
import ru.burtsev.push_keeper.presentation.screens.filter.FilterAppViewModel
import ru.burtsev.push_keeper.presentation.screens.notifications.NotificationsViewModel


val appModule = module {

    single<AppDatabase> { AppDatabase.createInstance(get()) }
    single<NotificationRepository> { NotificationRepositoryImpl(get()) }
    factory { NotificationInteractor(get()) }
    viewModel { NotificationsViewModel(get()) }
    viewModel { FilterAppViewModel(get()) }
}
