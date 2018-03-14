package com.example.chris.leafchat.repository

import com.example.chris.leafchat.BaseTest
import com.example.chris.leafchat.mocks.MockServiceHelper
import com.example.chris.leafchat.util.LeafSharedPreference
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import org.mockito.Mockito.*
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.mockito.ArgumentMatchers

/**
 * Created by Chris on 2/26/18.
 */
class LoginRepoTest : BaseTest() {
    @Inject lateinit var loginRepo: LoginRepository
    @Inject lateinit var sharedPreference: LeafSharedPreference

    private lateinit var spyRepository: LoginRepository
    private lateinit var loginCallback: LoginRepository.LoginCallback

    @Before
    override fun before() {
        super.before()

        component.inject(this)

        spyRepository = spy(loginRepo)
        loginCallback = Mockito.mock(LoginRepository.LoginCallback::class.java)

        spyRepository.call(loginCallback)
    }

    @Test
    fun testLoginUser() {
    }
}