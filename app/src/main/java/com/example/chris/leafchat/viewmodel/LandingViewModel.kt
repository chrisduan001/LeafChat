package com.example.chris.leafchat.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.StringRes
import com.example.chris.leafchat.R
import com.example.chris.leafchat.repository.LandingRepository
import javax.inject.Inject

/**
 * Created by Chris on 2/19/18.
 */
class LandingViewModel @Inject constructor(
        application: Application,
        private val landingRepository: LandingRepository)
    : BaseViewModel(application), LandingRepository.LandingCallback {

    val passCodeObserver: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val userNameObserver: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val progressObserver: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val toastMsgObserver: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        landingRepository.call(this)
    }

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

        landingRepository.loginUser(passcode, userName)
    }

    override fun onLoginSuccessful() {
        setObserverValue(progressObserver, false)
    }

    override fun onErrorWithId(@StringRes error: Int) {
        setObserverValue(toastMsgObserver, getContext().getString(error))
        setObserverValue(progressObserver, false)
    }
}
