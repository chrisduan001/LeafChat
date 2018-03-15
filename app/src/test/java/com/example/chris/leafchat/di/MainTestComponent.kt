package com.example.chris.leafchat.di

import com.example.chris.leafchat.di.modules.ApplicationModule
import com.example.chris.leafchat.di.modules.ChatRoomActivityModule
import com.example.chris.leafchat.repository.LoginRepoTest
import com.example.chris.leafchat.ui.adapters.UserListAdapterTest
import com.example.chris.leafchat.util.SharedPreferencesTest
import com.example.chris.leafchat.viewmodel.ChatRoomViewModelTest
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chris on 2/23/18.
 */
@Singleton
@Component(modules = [ApplicationModule::class, MockNetworkModule::class, ChatRoomActivityModule::class])
interface MainTestComponent {
    fun inject(sharedPrefTest: SharedPreferencesTest)

    //repository
    fun inject(loginRepoTest: LoginRepoTest)


    //viewmodel
    fun inject(chatVm: ChatRoomViewModelTest)

    //adapter
    fun inject(adapter: UserListAdapterTest)
}