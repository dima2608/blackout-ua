package com.august.ua.blackout

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        application = this

        //registerActivityLifecycleCallbacks(AppLifecycleTracker())

        //FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }

    companion object {
        lateinit var application: Application
    }
}