package ru.burtsev.push_keeper.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.burtsev.push_keeper.data.AppDatabase
import ru.burtsev.push_keeper.domain.NotificationInteractor
import ru.burtsev.push_keeper.domain.NotificationRepository
import ru.burtsev.push_keeper.domain.NotificationRepositoryImpl
import ru.burtsev.push_keeper.presentation.MainViewModel


val appModule = module {

    single<AppDatabase> { AppDatabase.createInstance(get()) }
    single<NotificationRepository> { NotificationRepositoryImpl(get()) }
    factory { NotificationInteractor(get()) }
    viewModel { MainViewModel(get()) }
}
