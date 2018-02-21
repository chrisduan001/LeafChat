package com.example.chris.leafchat.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.chris.leafchat.Logger
import com.example.chris.leafchat.R
import com.example.chris.leafchat.model.BaseResponse
import com.example.chris.leafchat.model.LoginModel
import com.example.chris.leafchat.network.ServiceHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chris on 2/19/18.
 */
class LandingViewModel @Inject constructor(application: Application, serviceHelper: ServiceHelper) : BaseViewModel(application, serviceHelper) {
    val passCodeObserver: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val userNameObserver: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val progressObserver: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun loginUser(passcode: String, userName: String) {
        if (passcode.length < 4) {
            setObserverValue(passCodeObserver, getContext().getString(R.string.error_invalid))
            return
        }

        if (userName.length < 5) {
            setObserverValue(userNameObserver, getContext().getString(R.string.error_invalid_name))
            return
        }

        setObserverValue(progressObserver, true)

        serviceHelper.loginUser(LoginModel(passcode, userName)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {t : BaseResponse -> Logger.log("response", t.toString()) },
                        {t : Throwable? -> Logger.log("response error", t?.localizedMessage)}
                )
    }
}
