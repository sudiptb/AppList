package com.applist.application

import android.app.Application
import com.applist.di.nm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }


    private fun setUpKoin() {
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(nm)
        }
    }
}