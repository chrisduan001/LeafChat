package com.example.chris.leafchat.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context

/**
 * Created by Chris on 2/21/18.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected fun getContext() : Context = getApplication<Application>().applicationContext

    protected fun <T> setObserverValue(observer: MutableLiveData<T>, data: T) {
        observer.value = data
    }
}