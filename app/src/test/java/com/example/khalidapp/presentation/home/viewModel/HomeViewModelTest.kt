package com.example.khalidapp.presentation.home.viewModel

import app.cash.turbine.test
import com.example.khalidapp.MainDispatcherRule
import com.example.khalidapp.domain.auth.model.User
import com.example.khalidapp.domain.auth.usecase.GetUserUseCase
import com.example.khalidapp.domain.auth.usecase.SignOutUseCase
import com.example.khalidapp.presentation.common.utils.Resource
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getUserUseCase: GetUserUseCase

    @MockK
    private lateinit var signOutUseCase: SignOutUseCase

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        homeViewModel = HomeViewModel(getUserUseCase, signOutUseCase)
    }


    @Test
    fun `get current user with success user data, test complete`() {
        runTest {
            val userResource = Resource.Success(User("test@mail.com", "test", "0", "Male"))

            val channel = Channel<Resource<User>>()
            val flow = channel.consumeAsFlow()

            every { getUserUseCase() } returns flow

            launch {
                channel.send(userResource)
            }

            withTimeout(10) {
                homeViewModel.userInfo.test {

                    homeViewModel.getUser()

                    val emissions = listOf(awaitItem(), awaitItem())

                    assertThat(emissions[1]).isEqualTo(userResource.data)
                    cancelAndConsumeRemainingEvents()
                }
            }

        }
    }
}