package com.example.khalidapp.view.startScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.khalidapp.databinding.ActivityStartBinding
import com.example.khalidapp.view.authScreen.AuthActivity

class StartActivity : AppCompatActivity() {

    private var binding: ActivityStartBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.lifecycleOwner = this
        binding!!.startActivity = this@StartActivity
    }

    fun navigateToAuthScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        this.startActivity(intent)
    }
}