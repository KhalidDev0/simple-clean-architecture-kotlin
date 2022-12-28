package com.example.khalidapp.presentation.start.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.khalidapp.databinding.ActivityStartBinding
import com.example.khalidapp.presentation.auth.AuthActivity

class StartActivity : AppCompatActivity() {

    private var binding: ActivityStartBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.apply {
            lifecycleOwner = this@StartActivity
            startActivity = this@StartActivity
        }
    }

    fun navigateToAuthScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        this.startActivity(intent)
        this.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}