package com.example.wb_week_7

import android.app.Application
import android.content.Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

companion object{
private var appInstance: App? =null
    fun getApplicationContext(): Context{
        return appInstance!!.applicationContext
    }
}

}