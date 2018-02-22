package com.example.chris.leafchat.repository

import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.LoginModel
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.util.ErrorHandler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Chris on 2/21/18.
 */
class LandingRepository @Inject constructor(serviceHelper: ServiceHelper, errorHandler: ErrorHandler) :
        BaseRepository<LandingRepository.LandingCallback>(serviceHelper, errorHandler) {

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