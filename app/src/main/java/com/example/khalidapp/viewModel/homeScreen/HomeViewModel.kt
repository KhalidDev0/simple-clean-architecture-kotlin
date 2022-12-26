package com.example.khalidapp.viewModel.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.common.utils.Resource
import com.example.khalidapp.data.authRepository.AuthRepository
import com.example.khalidapp.data.userRepo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _userInfo = MutableLiveData<String>()
    val userInfo: LiveData<String> = _userInfo

    private val _navigateToStart = MutableLiveData(false)
    val navigateToStart: LiveData<Boolean> = _navigateToStart

    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            val resource = userRepository.getCurrentUser()
            when(resource){
                is Resource.Success -> {
                    _userInfo.value = resource.data.toString()
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }

        }
    }

    fun logout(){
        viewModelScope.launch {
            when(authRepository.logout()){
                is Resource.Success -> {
                    _navigateToStart.value = true
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }

        }
    }
}