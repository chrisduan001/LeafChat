package com.example.chris.leafchat.viewmodel

import android.arch.lifecycle.ViewModelProvider
import com.example.chris.leafchat.BaseTest
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.MockNetworkModule
import com.example.chris.leafchat.repository.LoginRepository
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import javax.inject.Inject
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.mockito.Mockito.*

/**
 * Created by Chris on 2/26/18.
 */
class LoginViewModelTest : BaseTest() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepo: LoginRepository

    @Before
    override fun before() {
        super.before()

        loginRepo = Mockito.mock(LoginRepository::class.java)
        loginViewModel = LoginViewModel(application, loginRepo)
    }

    @Test
    fun testLoginUser() {
        Mockito.doNothing().`when`(loginRepo).loginUser(anyString(), anyString())
        loginViewModel.loginUser("t1111", "t11111")
        assertThat(loginViewModel.progressObserver.value, `is`(true))
    }

    @Test
    fun testCallback() {
        loginViewModel.onErrorWithId(R.string.error_passcode_error)
        loginViewModel.onLoginSuccessful()

        assertThat(loginViewModel.loginObserver.value, `is`(true))
        assertThat(loginViewModel.progressObserver.value, `is`(false))
        assertThat(loginViewModel.toastMsgObserver.value, `is`(application.getString(R.string.error_passcode_error)))
    }
}