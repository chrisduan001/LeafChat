package com.example.chris.leafchat.network

import android.support.annotation.VisibleForTesting
import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.LoginModel
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Chris on 2/21/18.
 * class and methods are marked as open for testing purposes
 */
open class ServiceHelper constructor(private val leafChatApi: LeafChatApi) {
    open fun loginUser(loginModel: LoginModel) : Single<BaseResponse> {
        return leafChatApi.loginUser(loginModel)
    }

    open fun logoutUser(userName: String) : Single<BaseResponse> {
        return leafChatApi.logoutUser(userName)
    }
}