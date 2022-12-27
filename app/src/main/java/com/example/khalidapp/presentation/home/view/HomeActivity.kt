package com.example.khalidapp.presentation.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.khalidapp.databinding.ActivityHomeBinding
import com.example.khalidapp.presentation.home.viewModel.HomeViewModel
import com.example.khalidapp.presentation.start.view.StartActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    var binding: ActivityHomeBinding? = null
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

    private fun observe() {
        viewModel.navigateToStart.observe(this) {
            if (it) {
                startActivity(Intent(this, StartActivity::class.java))
                this.finish()
            }
        }
    }
}

