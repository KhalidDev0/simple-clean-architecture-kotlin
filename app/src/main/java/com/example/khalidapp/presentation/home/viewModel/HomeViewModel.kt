package com.example.khalidapp.presentation.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.usecase.GetUserUseCase
import com.example.khalidapp.domain.auth.usecase.SignOutUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _userInfo = MutableStateFlow(User("","","","",))
    val userInfo: StateFlow<User> = _userInfo

    private val _navigateToStart = MutableStateFlow(false)
    val navigateToStart: StateFlow<Boolean> = _navigateToStart

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            when (val resource = getUserUseCase()) {
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
            when (signOutUseCase()) {
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