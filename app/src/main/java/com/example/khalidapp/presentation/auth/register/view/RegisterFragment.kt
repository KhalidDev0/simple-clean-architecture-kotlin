package com.example.khalidapp.presentation.auth.register.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.khalidapp.databinding.FragmentRegisterBinding
import com.example.khalidapp.presentation.auth.register.viewModel.RegisterViewModel
import com.example.khalidapp.presentation.home.view.HomeActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var binding: FragmentRegisterBinding? = null
    private val viewModel: RegisterViewModel by viewModels()

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

        //observe ViewModel
        observe()

        binding!!.apply {
            lifecycleOwner = this@RegisterFragment
            viewModel = this@RegisterFragment.viewModel
        }
    }

    private fun observe() {
        lifecycleScope.launch{
            launch {
                viewModel.registerError.collectLatest {
                    Snackbar.make(
                        binding?.root!!, it,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            launch {
                viewModel.navigateToHome.collect {
                    if (it) {
                        startActivity(Intent(requireContext(), HomeActivity::class.java))
                        requireActivity().finish()
                    }
                }
            }
            launch {
                viewModel.isLoading.collectLatest {
                    binding?.loadingBar?.visibility = it
                }
            }
            launch {
                viewModel.emailValidationError.collectLatest {
                    binding?.textEmailAddress?.error = it
                }
            }
            launch {
                viewModel.passwordValidationError.collectLatest {
                    binding?.textPassword?.error = it
                }
            }
            launch {
                viewModel.nameValidationError.collectLatest {
                    binding?.textName?.error = it
                }
            }
            launch {
                viewModel.ageValidationError.collectLatest {
                    binding?.textAge?.error = it
                }
            }
            launch {
                viewModel.genderValidationError.collectLatest {
                    binding?.femaleButton?.error = it
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}