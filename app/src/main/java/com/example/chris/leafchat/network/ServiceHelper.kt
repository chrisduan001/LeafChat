package com.example.chris.leafchat.network

import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.LoginModel
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Chris on 2/21/18.
 */
class ServiceHelper constructor(private val leafChatApi: LeafChatApi) {
    fun loginUser(loginModel: LoginModel) : Single<BaseResponse> {
        return leafChatApi.loginUser(loginModel)
    }

    fun logoutUser(userName: String) : Single<BaseResponse> {
        return leafChatApi.logoutUser(userName)
    }
}