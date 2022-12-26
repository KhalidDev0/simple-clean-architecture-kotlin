package com.example.khalidapp.viewModel.homeScreen

import androidx.lifecycle.ViewModel
import com.example.khalidapp.data.userRepo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {
}