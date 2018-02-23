package com.example.chris.leafchat.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.chris.leafchat.Logger

/**
 * Created by Chris on 2/23/18.
 */
class ChatRoomActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            val bundle = intent.extras
            val userName = bundle.getString(USER_NAME)
            val passcode = bundle.getString(PASSCODE)
            Logger.log("user name pass code", userName + " " + passcode)
        }
    }

    companion object {
        private const val USER_NAME = "USER_NAME"
        private const val PASSCODE = "PASSCODE"

        fun newInstance(context: Context, userName: String, passcode: String) : Intent {
            val bundle = Bundle()
            bundle.putString(USER_NAME, userName)
            bundle.putString(PASSCODE, passcode)

            val intent = Intent(context, ChatRoomActivity::class.java)
            intent.putExtras(bundle)

            return intent
        }
    }
}