package com.example.chris.leafchat.repository

import com.example.chris.leafchat.model.LoginModel
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.util.ErrorHandler
import com.example.chris.leafchat.util.LeafSharedPreference
import javax.inject.Inject

/**
 * Created by Chris on 2/21/18.
 */
class LoginRepository @Inject constructor(
        serviceHelper: ServiceHelper,
        errorHandler: ErrorHandler,
        private val sharedPreference: LeafSharedPreference) :
        BaseRepository<LoginRepository.LandingCallback>(
                serviceHelper,
                errorHandler) {

    fun loginUser(passCode: String, userName: String) {
        executeNetworkCall(
                { serviceHelper!!.loginUser(LoginModel(passCode, userName)) },
                { _ ->
                    sharedPreference.setPasscode(passCode)
                    sharedPreference.setUserName(userName)
                    listener?.onLoginSuccessful()
                },
                {
                    sharedPreference.clearSharedPreference()
                }
        )
    }

    interface LandingCallback : BaseRepoCallback {
        fun onLoginSuccessful()
    }
}