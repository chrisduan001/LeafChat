package com.example.chris.leafchat.repository

import com.example.chris.leafchat.model.AllUserResponse
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.util.ErrorHandler
import com.example.chris.leafchat.util.LeafSharedPreference
import javax.inject.Inject

/**
 * Created by Chris on 3/14/18.
 */
class ChatRoomRepository @Inject constructor(
        serviceHelper: ServiceHelper,
        errorHandler: ErrorHandler) :
        BaseRepository<ChatRoomRepository.ChatRoomCallback>(serviceHelper, errorHandler) {

    fun getAllUsers() {
        executeNetworkCall(
                { serviceHelper!!.getAllUsers() },
                { t: AllUserResponse -> listener?.onGetAllUser(t.users.split(",")) },
                {} //ignore the error as it was handled in super class
        )
    }

    interface ChatRoomCallback : BaseRepoCallback {
        fun onGetAllUser(users: List<String>)
    }
}