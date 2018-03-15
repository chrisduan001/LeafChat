package com.example.chris.leafchat.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.HasComponent
import com.example.chris.leafchat.di.components.ChatRooomComponent
import com.example.chris.leafchat.di.components.DaggerChatRooomComponent
import com.example.chris.leafchat.ui.fragment.ChatRoomFragment
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * Created by Chris on 2/23/18.
 */
class ChatRoomActivity : BaseActivity(), HasComponent<ChatRooomComponent> {

    //region init
    private val chatRoomComponent: ChatRooomComponent by lazy {
        DaggerChatRooomComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic)

        initToolbar()

        setupFragment()

        setupViewModel()
    }

    private fun setupFragment() {
        val bundle = intent.extras
        val userName = bundle.getString(USER_NAME)
        val passcode = bundle.getString(PASSCODE)

        performFragmentTransaction(ChatRoomFragment.newInstance(userName, passcode))
    }

    override fun initToolbar() {
        super.initToolbar()

        supportActionBar?.setDisplayShowTitleEnabled(false)

        view_toolbar.showTitle(R.string.chatroom_title)
    }

    override fun getComponent(): ChatRooomComponent {
        return chatRoomComponent
    }
    //endregion

    //region viewmodel
    private fun setupViewModel() {

    }
    //endregion

    companion object {
        const val USER_NAME = "USER_NAME"
        const val PASSCODE = "PASSCODE"

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