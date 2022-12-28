package com.example.khalidapp.presentation.auth.login.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.khalidapp.R
import com.example.khalidapp.databinding.FragmentLoginBinding
import com.example.khalidapp.presentation.auth.login.viewModel.LoginViewModel
import com.example.khalidapp.presentation.home.view.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observe ViewModel
        observe()

        binding?.apply {
            lifecycleOwner = this@LoginFragment
            loginFragment = this@LoginFragment
            viewModel = this@LoginFragment.viewModel
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.navigateToHome.collect {
                if (it) {
                    startActivity(Intent(requireContext(), HomeActivity::class.java))
                    requireActivity().finish()
                }
            }
        }
    }

    fun navigateToRegisterScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}