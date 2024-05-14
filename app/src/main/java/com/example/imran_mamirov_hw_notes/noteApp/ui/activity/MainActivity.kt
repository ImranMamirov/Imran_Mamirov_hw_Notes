package com.example.imran_mamirov_hw_notes.noteApp.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.imran_mamirov_hw_notes.R
import com.example.imran_mamirov_hw_notes.databinding.ActivityMainBinding
import com.example.imran_mamirov_hw_notes.noteApp.utils.SharedPreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment

        navController = navHostFragment.navController

        sharedPreferenceHelper = SharedPreferenceHelper()
        sharedPreferenceHelper.init(this@MainActivity)

        if (sharedPreferenceHelper.isShowed()){
            navController.navigate(R.id.noteFragment)
            sharedPreferenceHelper.onShowed()
        }

//        if (!SharedPreferences.isOnBoardShown(this)) {
//            findNavController(R.id.nav_fragment).navigate(R.id.onBoardFragment)
//            SharedPreferences.setisOnBoardShown(this, true)
//        }
    }
}