package com.example.cryptocurrencyapp.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.NavigationUI
import androidx.navigation.findNavController
import com.example.cryptocurrencyapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.navFragment))
    }

    override fun onSupportNavigateUp() = findNavController(R.id.navFragment).navigateUp()
}