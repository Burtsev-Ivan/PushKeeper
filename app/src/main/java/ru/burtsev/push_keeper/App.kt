package ru.burtsev.push_keeper

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.burtsev.push_keeper.presentation.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
//            androidLogger() // todo не работает с версией котлина 1.5
            modules(appModule)
        }
    }
}