package com.example.chris.leafchat.network

import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.LoginModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

/**
 * Created by Chris on 2/21/18.
 */
interface LeafChatApi {
    @POST("/api/chatlogin")
    fun loginUser(@Body loginModel: LoginModel) : Single<BaseResponse>

    @POST("/api/chatLogout")
    fun logoutUser(@Field("userName") userName: String) : Single<BaseResponse>
}