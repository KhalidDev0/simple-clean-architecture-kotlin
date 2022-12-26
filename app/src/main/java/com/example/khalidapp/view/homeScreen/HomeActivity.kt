package com.example.khalidapp.view.homeScreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.khalidapp.R
import com.example.khalidapp.databinding.ActivityHomeBinding
import com.example.khalidapp.databinding.FragmentLoginBinding
import com.example.khalidapp.viewModel.authScreen.loginFragment.LoginViewModel
import com.example.khalidapp.viewModel.homeScreen.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    var binding : ActivityHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}

