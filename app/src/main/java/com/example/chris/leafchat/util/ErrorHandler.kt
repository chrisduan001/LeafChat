package com.example.chris.leafchat.util

import com.example.chris.leafchat.R
import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.ParentResponse
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Created by Chris on 2/21/18.
 */
class ErrorHandler {
    fun checkApiResponseError(response: ParentResponse) : Int? {
        response.error?.let {
            //api response contains error
            return when (it.errorCode) {
                1000 -> R.string.error_name_used
                1001 -> R.string.error_passcode_error
                else -> R.string.error_generic
            }
        }

        return null
    }

    fun checkRxJavaError(throwable: Throwable?) : Int {
        throwable?.let {
            return when (it) {
                is UnknownHostException, is ConnectException -> R.string.error_connection
                else -> R.string.error_generic
            }
        }

        return R.string.error_generic
    }
}