package com.example.khalidapp.view.authScreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.khalidapp.databinding.FragmentRegisterBinding
import com.example.khalidapp.view.homeScreen.HomeActivity
import com.example.khalidapp.viewModel.authScreen.registerFragment.RegisterViewModel

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

    private fun observe(){
        viewModel.navigateToHome.observe(viewLifecycleOwner){
            if (it){
                startActivity(Intent(requireContext(), HomeActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}