package com.example.chris.leafchat.ui.fragment

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.components.ChatRooomComponent
import com.example.chris.leafchat.ui.activity.ChatRoomActivity
import com.example.chris.leafchat.viewmodel.ChatRoomViewModel
import kotlinx.android.synthetic.main.fragment_chat_room.*
import javax.inject.Inject

/**
 * Created by Chris on 3/6/18.
 */
class ChatRoomFragment : BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var chatRoomVm: ChatRoomViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat_room, container, false)

        initInjector()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
    }

    private fun initInjector() {
        getComponent(ChatRooomComponent::class.java).inject(this)

        chatRoomVm = getViewModel(ChatRoomViewModel::class.java, viewModelFactory)
    }

    private fun setupViews() {
        view_username.text = arguments?.getString(ChatRoomActivity.USER_NAME)
        view_password.text = arguments?.getString(ChatRoomActivity.PASSCODE)
    }

    companion object {
        fun newInstance(userName: String, passCode: String) : ChatRoomFragment {

            val fragment = ChatRoomFragment()
            fragment.arguments = Bundle().apply {
                putString(ChatRoomActivity.USER_NAME, userName)
                putString(ChatRoomActivity.PASSCODE, passCode)
            }

            return fragment
        }
    }
}