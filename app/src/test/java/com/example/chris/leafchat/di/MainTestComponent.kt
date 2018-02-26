package com.example.chris.leafchat.di

import com.example.chris.leafchat.BaseTest
import com.example.chris.leafchat.di.components.ApplicationComponent
import com.example.chris.leafchat.di.modules.ApplicationModule
import com.example.chris.leafchat.mocks.MockServiceHelper
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.repository.LoginRepoTest
import com.example.chris.leafchat.util.ErrorHandler
import com.example.chris.leafchat.util.LeafSharedPreference
import com.example.chris.leafchat.util.SharedPreferencesTest
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chris on 2/23/18.
 */
@Singleton
@Component(modules = [ApplicationModule::class, MockNetworkModule::class])
interface MainTestComponent {
    fun inject(sharedPrefTest: SharedPreferencesTest)

    //repository
    fun inject(loginRepoTest: LoginRepoTest)


}