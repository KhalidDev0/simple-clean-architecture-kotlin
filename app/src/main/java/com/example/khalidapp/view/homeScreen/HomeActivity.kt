package com.example.khalidapp.view.homeScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import com.example.khalidapp.R
import com.example.khalidapp.databinding.ActivityHomeBinding
import com.example.khalidapp.databinding.ActivityStartBinding
import com.example.khalidapp.databinding.FragmentLoginBinding
import com.example.khalidapp.view.startScreen.StartActivity
import com.example.khalidapp.viewModel.authScreen.loginFragment.LoginViewModel
import com.example.khalidapp.viewModel.homeScreen.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    var binding : ActivityHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //observe ViewModel
        observe()

        binding!!.apply {
            lifecycleOwner = this@HomeActivity
            viewModel = this@HomeActivity.viewModel
        }
    }

    private fun observe(){
        viewModel.navigateToStart.observe(this){
            if (it){
                startActivity(Intent(this, StartActivity::class.java))
                this.finish()
            }
        }
    }
}

