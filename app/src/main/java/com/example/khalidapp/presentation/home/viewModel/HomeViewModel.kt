package com.example.khalidapp.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.usecase.GetUserUseCase
import com.example.khalidapp.domain.auth.usecase.SignOutUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _userInfo = MutableStateFlow(User("ywefoyhskfyk", "", "", ""))
    val userInfo: StateFlow<User> = _userInfo

    private val _navigateToStart = MutableStateFlow(false)
    val navigateToStart: StateFlow<Boolean> = _navigateToStart

    init {
        getUser()
    }

    fun getUser() {
        getUserUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _userInfo.value = resource.data
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                    _navigateToStart.emit(true)
                }
            }
        }.launchIn(viewModelScope)
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