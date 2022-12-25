package com.example.khalidapp.view.authScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.khalidapp.databinding.FragmentRegisterBinding
import com.example.khalidapp.view.homeScreen.HomeActivity
import com.example.khalidapp.viewModel.authScreen.registerFragment.RegisterResultCallBack
import com.example.khalidapp.viewModel.authScreen.registerFragment.RegisterViewModel
import com.example.khalidapp.viewModel.authScreen.registerFragment.RegisterViewModelFactory

class RegisterFragment : Fragment(), RegisterResultCallBack {

    private var binding: FragmentRegisterBinding? = null
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, RegisterViewModelFactory(this))[RegisterViewModel::class.java]

        binding!!.apply {
            lifecycleOwner = this@RegisterFragment
            viewModel = this@RegisterFragment.viewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onRegisterSuccess() {
        Log.d("TEST", "Registered successfully")

        //Temporary Navigation
        startActivity(Intent(this.context, HomeActivity::class.java))
    }


    override fun onRegisterFailed(message: String) {
        Log.d("TEST", "message: $message")
    }
}