package com.example.khalidapp.view.startScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.khalidapp.R
import com.example.khalidapp.viewModel.StartScreenViewModel
import androidx.activity.viewModels

class StartActivity : AppCompatActivity() {

    private val TAG = "StartActivity"
    private val viewModel : StartScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
}