package com.example.chris.leafchat.viewmodel

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import com.example.chris.leafchat.LeafApplication
import com.example.chris.leafchat.Logger
import javax.inject.Inject

/**
 * Created by Chris on 2/19/18.
 */
class LandingViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    fun testValue() {

        Logger.log(LandingViewModel::class.java.name, "clicked")
    }
}
