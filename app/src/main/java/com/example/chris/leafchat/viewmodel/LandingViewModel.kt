package com.example.chris.leafchat.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.chris.leafchat.R
import javax.inject.Inject

/**
 * Created by Chris on 2/19/18.
 */
class LandingViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
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
    }
}
