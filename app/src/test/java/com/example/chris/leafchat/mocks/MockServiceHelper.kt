package com.example.chris.leafchat.mocks

import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.LoginModel
import com.example.chris.leafchat.network.LeafChatApi
import com.example.chris.leafchat.network.ServiceHelper
import io.reactivex.Single
import java.net.ConnectException

/**
 * Created by Chris on 2/26/18.
 */


class MockServiceHelper(api: LeafChatApi) : ServiceHelper(api) {

    override fun loginUser(loginModel: LoginModel): Single<BaseResponse> {
        return when (loginModel.userName) {
            TEST_STATUS_ERROR_0 -> Single.error(ConnectException())
            else -> Single.just(BaseResponse(null))
        }
    }

    override fun logoutUser(userName: String): Single<BaseResponse> {
        return Single.just(BaseResponse(null))
    }

    companion object {
        const val TEST_STATUS_ERROR_0 = "ERROR_0"
    }
}