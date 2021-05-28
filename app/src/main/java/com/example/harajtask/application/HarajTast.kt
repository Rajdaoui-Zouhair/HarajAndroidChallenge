package com.example.harajtask.application

import android.app.Application
import android.content.Context

class HarajTast: Application() {

    companion object {
        var context: Context? = null
    }
    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }
}