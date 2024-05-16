package com.example.imran_mamirov_hw_notes.noteApp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "shared",
        Context.MODE_PRIVATE
    )

    fun setOnBoardingComplete(isComplete: Boolean) {
        sharedPreferences.edit().putBoolean(SHOWED, isComplete).apply()
    }

    fun isOnBoardingComplete(): Boolean {
        return sharedPreferences.getBoolean(SHOWED, false)
    }

    companion object{
        const val SHOWED = "SHOWED"
    }
}