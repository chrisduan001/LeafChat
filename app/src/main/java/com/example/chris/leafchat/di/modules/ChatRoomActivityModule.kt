package com.example.chris.leafchat.di.modules

import android.app.Activity
import com.example.chris.leafchat.di.PerActivity
import com.example.chris.leafchat.ui.activity.ChatRoomActivity
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Chris on 3/14/18.
 */
@Module
class ChatRoomActivityModule(private val activity: ChatRoomActivity) {

    @Provides @PerActivity
    fun provideUserList() : List<String> {
        return emptyList()
    }

    @Provides @PerActivity
    fun provideActivity(): ChatRoomActivity {
        return activity
    }
}