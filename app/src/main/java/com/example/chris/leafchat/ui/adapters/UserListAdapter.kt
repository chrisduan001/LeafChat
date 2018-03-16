package com.example.chris.leafchat.ui.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.chris.leafchat.R
import com.example.chris.leafchat.util.DiffCallback
import com.example.chris.leafchat.viewmodel.ChatRoomViewModel

/**
 * Created by Chris on 3/14/18.
 */
class UserListAdapter constructor(
        private var users: List<String>,
        private val chatRoomVm: ChatRoomViewModel) : RecyclerView.Adapter<UserVH>() {

    fun updateItems(newList: List<String>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(newList, users))

        users = newList

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserVH {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false)

        return UserVH(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserVH?, position: Int) {
        holder?.let {
            val name = users[position]
            it.userName.text = name

            it.connectBtn.setOnClickListener { onConnectUser(name) }
        }
    }

    private fun onConnectUser(name: String) {
        chatRoomVm.requestConnection(name)
    }
}

class UserVH(view: View) : RecyclerView.ViewHolder(view) {
    val userName: TextView = view.findViewById(R.id.view_usernameTv)
    val connectBtn: Button = view.findViewById(R.id.view_connectBtn)
}