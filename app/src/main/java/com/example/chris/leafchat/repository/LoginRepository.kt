package com.example.chris.leafchat.repository

import com.example.chris.leafchat.model.LoginModel
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.util.ErrorHandler
import javax.inject.Inject

/**
 * Created by Chris on 2/21/18.
 */
class LoginRepository @Inject constructor(serviceHelper: ServiceHelper, errorHandler: ErrorHandler) :
        BaseRepository<LoginRepository.LandingCallback>(serviceHelper, errorHandler) {

    fun loginUser(passCode: String, userName: String) {
        executeNetworkCall(
                { serviceHelper!!.loginUser(LoginModel(passCode, userName)) },
                { _ -> listener?.onLoginSuccessful() }
        )
    }

    interface LandingCallback : BaseRepoCallback {
        fun onLoginSuccessful()
    }
}