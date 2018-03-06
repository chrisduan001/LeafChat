package com.example.chris.leafchat.di.modules

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.chris.leafchat.di.ViewModelKey
import com.example.chris.leafchat.viewmodel.ChatRoomViewModel
import com.example.chris.leafchat.viewmodel.LoginViewModel
import com.example.chris.leafchat.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Chris on 2/19/18.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLandingViewModel(loginViewModel: LoginViewModel): AndroidViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatRoomViewModel::class)
    abstract fun bindChatRoomViewModel(chatRoomViewModel: ChatRoomViewModel) : AndroidViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

