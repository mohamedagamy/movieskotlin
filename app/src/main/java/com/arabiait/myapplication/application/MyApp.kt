package com.arabiait.myapplication.application

import android.app.Application
import com.facebook.stetho.Stetho
import io.realm.Realm

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Stetho.initializeWithDefaults(this)

    }
}