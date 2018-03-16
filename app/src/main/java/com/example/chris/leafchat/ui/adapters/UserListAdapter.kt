package com.example.chris.leafchat.ui.adapters

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chris.leafchat.R
import com.example.chris.leafchat.ui.activity.ChatRoomActivity
import com.example.chris.leafchat.util.DiffCallback
import com.example.chris.leafchat.viewmodel.ChatRoomViewModel
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Chris on 3/14/18.
 */
class UserListAdapter @Inject constructor(
        private var users: List<String>,
        private val activity: ChatRoomActivity,
        private val vmFactory: ViewModelProvider.Factory) : RecyclerView.Adapter<UserVH>() {

    private val chatRoomVm: ChatRoomViewModel by lazy {
        ViewModelProviders.of(activity, vmFactory).get(ChatRoomViewModel::class.java)
    }

    fun updateItems(newList: List<String>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(newList, users))

        users = newList

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserVH {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false)

        return UserVH(view) { t -> onSelectUser(t)}
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserVH?, position: Int) {
        holder?.let {
            val name = users[position]
            it.userName.text = name
        }
    }

    private fun onSelectUser(pos: Int) {
        chatRoomVm.requestConnection(users[pos])
    }
}

class UserVH(view: View, onClick: (pos: Int) -> Unit) : RecyclerView.ViewHolder(view) {
    val userName: TextView = view.findViewById(R.id.view_usernameTv)

    init {
        view.setOnClickListener { onClick(adapterPosition) }
    }
}