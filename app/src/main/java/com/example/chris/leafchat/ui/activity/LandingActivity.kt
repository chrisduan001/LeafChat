package com.example.chris.leafchat.ui.activity

import android.os.Bundle
import com.example.chris.leafchat.util.LeafSharedPreference
import com.example.chris.leafchat.util.Navigator
import javax.inject.Inject

/**
 * Created by Chris on 2/23/18.
 */
class LandingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userName = sharedPreference.getUserName()
        val passcode = sharedPreference.getPasscode()

        if (userName.isEmpty() || passcode.isEmpty()) {
            navigator.navigateToLoginActivity()
        } else {
            navigator.navigateToChatRoom(userName, passcode)
        }

        finish()
    }
}