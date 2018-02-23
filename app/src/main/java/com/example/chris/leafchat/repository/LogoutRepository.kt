package com.example.chris.leafchat.repository

import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.util.ErrorHandler
import javax.inject.Inject

/**
 * Created by Chris on 2/22/18.
 */
class LogoutRepository @Inject constructor(serviceHelper: ServiceHelper, errorHandler: ErrorHandler)
    : BaseRepository<BaseRepoCallback>(serviceHelper, errorHandler) {

    fun logoutUser(userName: String) {
        executeNetworkCall({serviceHelper!!.logoutUser(userName)}, {})
    }
}