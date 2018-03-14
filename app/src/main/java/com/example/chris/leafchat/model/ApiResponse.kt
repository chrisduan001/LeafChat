package com.example.chris.leafchat.model

/**
 * Created by Chris on 2/21/18.
 */
abstract class ParentResponse {
    abstract val error: ErrorResponse?
}
data class BaseResponse(override val error: ErrorResponse?) : ParentResponse()
data class ErrorResponse(val errorCode: Int, val message: String)

data class AllUserResponse(override val error: ErrorResponse?, val users: String) : ParentResponse()