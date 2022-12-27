package com.example.khalidapp.presentation.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.repository.AuthRepository
import com.example.khalidapp.domain.auth.repository.UserRepository
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> = _userInfo

    private val _navigateToStart = MutableLiveData(false)
    val navigateToStart: LiveData<Boolean> = _navigateToStart

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val resource = userRepository.getCurrentUser()
            when (resource) {
                is Resource.Success -> {
                    _userInfo.value = resource.data
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }

        }
    }

    fun logout() {
        viewModelScope.launch {
            when (authRepository.logout()) {
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