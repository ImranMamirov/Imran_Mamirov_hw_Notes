package com.example.imran_mamirov_hw_notes.noteApp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    fun onShowed() {
        sharedPreferences.edit().putBoolean(SHOWED, false).apply()
    }

    fun isShowed(): Boolean {
        return sharedPreferences.getBoolean(SHOWED, true)
    }

    companion object {
        const val SHOWED = "SHOWED"
    }

//    var title: String?
//        get() = sharedPreferences.getString("title", "")
//        set(value) = sharedPreferences.edit().putString("title", value).apply()

//    var isOnBoardShown: Boolean
//        get() = sharedPreferences.getBoolean("board", false)
//        set(value) = sharedPreferences.edit().putBoolean("board", value).apply()
}