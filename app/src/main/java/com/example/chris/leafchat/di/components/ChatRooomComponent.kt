package com.example.chris.leafchat.di.components

import com.example.chris.leafchat.di.PerActivity
import com.example.chris.leafchat.di.modules.ChatRoomActivityModule
import com.example.chris.leafchat.ui.fragment.ChatRoomFragment
import dagger.Component

/**
 * Created by Chris on 3/6/18.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ChatRoomActivityModule::class])
interface ChatRooomComponent {
    fun inject(fragment: ChatRoomFragment)

    fun userList() : List<String>
}