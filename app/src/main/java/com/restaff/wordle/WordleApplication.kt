package com.restaff.wordle

import android.app.Application
import com.restaff.wordle.di.repositoryModule
import com.restaff.wordle.di.retrofitModule
import com.restaff.wordle.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class WordleApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        org.koin.core.context.startKoin {
            androidLogger(Level.NONE)
            androidContext(applicationContext)
            modules(
                retrofitModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}