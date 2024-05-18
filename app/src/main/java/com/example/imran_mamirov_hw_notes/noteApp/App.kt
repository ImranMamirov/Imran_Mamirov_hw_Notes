package com.example.imran_mamirov_hw_notes.noteApp

import android.app.Application
import androidx.room.Room
import com.example.imran_mamirov_hw_notes.noteApp.data.db.AppDataBase
import com.example.imran_mamirov_hw_notes.noteApp.utils.SharedPreferenceHelper

class App : Application() {

    companion object {
        var appDatabase: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        getInstance()
    }

    fun getInstance(): AppDataBase? {
        if (appDatabase == null) {
            appDatabase = applicationContext.let {
                Room.databaseBuilder(
                    it,
                    AppDataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}