package com.example.chris.leafchat.repository

import android.support.annotation.CallSuper
import android.support.annotation.NonNull
import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.ParentResponse
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.util.ErrorHandler
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Chris on 2/21/18.
 */
abstract class BaseRepository<LISTENER : BaseRepoCallback> constructor(
        protected val serviceHelper: ServiceHelper? = null,
        protected val errorHandler: ErrorHandler? = null) {

    protected var listener: LISTENER? = null

    @CallSuper
    open fun call(listener: LISTENER) {
        this.listener = listener
    }

    protected inline fun <RESPONSE : ParentResponse> executeNetworkCall(
            crossinline request: () -> Single<RESPONSE>,
            crossinline successful: (t: RESPONSE) -> Unit) {

        //ErrorHandler must not be null when serviceHelper is used
        request().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { t: RESPONSE ->
                            errorHandler!!.checkApiResponseError(t)?.let {
                                listener?.onErrorWithId(it)

                                return@subscribe
                            }

                            successful(t)
                        },
                        { t: Throwable? -> listener?.onErrorWithId(errorHandler!!.checkRxJavaError(t)) }
                )
    }
}