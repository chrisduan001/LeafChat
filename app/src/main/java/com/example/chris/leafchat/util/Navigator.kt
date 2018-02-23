package com.example.chris.leafchat.util

import android.app.Application
import android.content.Context
import com.example.chris.leafchat.LeafApplication
import com.example.chris.leafchat.ui.activity.ChatRoomActivity
import com.example.chris.leafchat.ui.activity.LoginActivity
import javax.inject.Inject

/**
 * Created by Chris on 2/15/18.
 */
class Navigator @Inject constructor(private val application: Application) {

    fun navigateToLoginActivity() {
        application.startActivity(LoginActivity.newInstance(application))
    }

    fun navigateToChatRoom(userName: String, passcode: String) {
        application.startActivity(ChatRoomActivity.newInstance(application, userName, passcode))
    }
}