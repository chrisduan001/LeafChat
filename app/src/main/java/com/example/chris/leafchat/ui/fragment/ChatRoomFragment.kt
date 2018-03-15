package com.example.chris.leafchat.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.chris.leafchat.R
import com.example.chris.leafchat.di.components.ChatRooomComponent
import com.example.chris.leafchat.ui.activity.ChatRoomActivity
import com.example.chris.leafchat.ui.adapters.UserListAdapter
import com.example.chris.leafchat.viewmodel.ChatRoomViewModel
import kotlinx.android.synthetic.main.fragment_chat_room.*
import kotlinx.android.synthetic.main.item_user.*
import javax.inject.Inject

/**
 * Created by Chris on 3/6/18.
 */
class ChatRoomFragment : BaseFragment() {

    @Inject lateinit var usersAdapter : UserListAdapter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var chatRoomVm: ChatRoomViewModel

    //region init & set up views
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

        setupViewModel()

        chatRoomVm.getAllUsers()
    }

    private fun setupViews() {
        view_recyclerview.layoutManager = LinearLayoutManager(activity)
        view_recyclerview.adapter = usersAdapter
    }
    //endregion

    //region viewmodel & observer
    private fun setupViewModel() {
        chatRoomVm.progressObserver.observe(this, progressOb)
        chatRoomVm.toastMsgObserver.observe(this, toastOb)
        chatRoomVm.userListObserver.observe(this, usersOb)
    }

    private val progressOb = Observer<Boolean> {
        view_progressBar.visibility = if (it == true) View.VISIBLE else View.GONE
    }

    private val toastOb = Observer<String> {
        if (!it.isNullOrEmpty()) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }

    private val usersOb = Observer<List<String>> {
        if (it != null) {
            usersAdapter.updateItems(it)
        }
    }
    //endregion

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