package com.example.chris.leafchat.mocks

import com.example.chris.leafchat.model.AllUserResponse
import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.ErrorResponse
import com.example.chris.leafchat.model.LoginModel
import com.example.chris.leafchat.network.LeafChatApi
import com.example.chris.leafchat.network.ServiceHelper
import io.reactivex.Single
import java.net.ConnectException

/**
 * Created by Chris on 2/26/18.
 */


class MockServiceHelper(api: LeafChatApi) : ServiceHelper(api) {

    var serviceStatus = STATUS_SUCCESS

    override fun loginUser(loginModel: LoginModel): Single<BaseResponse> {
        return when (serviceStatus) {
            STATUS_WITH_ERROR_RESPONSE -> Single.error(Throwable("error"))
            else -> Single.just(BaseResponse(null))
        }
    }

    override fun logoutUser(userName: String): Single<BaseResponse> {
        return Single.just(BaseResponse(null))
    }

    override fun getAllUsers(): Single<AllUserResponse>  =
            when (serviceStatus) {
                STATUS_SUCCESS -> Single.just(AllUserResponse(null, "user1,user2,user3"))
                GENERIC_ERROR ->
                    Single.just(AllUserResponse(
                            ErrorResponse(GENERIC_ERROR, "server error"),
                            ""))
                else -> Single.error(Throwable("error"))
            }

    companion object {
        const val STATUS_SUCCESS = 0
        const val STATUS_WITH_ERROR_RESPONSE = 1
        const val GENERIC_ERROR = 99999
    }
}