package com.example.imran_mamirov_hw_notes.noteApp

import android.app.Application
import com.example.imran_mamirov_hw_notes.noteApp.utils.SharedPreferenceHelper

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = SharedPreferenceHelper()
        sharedPreferences.init(this@App)
    }
}