package com.hsj.wantedwork

import android.app.Application
import com.hsj.wantedwork.di.myDiModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)
    }
}