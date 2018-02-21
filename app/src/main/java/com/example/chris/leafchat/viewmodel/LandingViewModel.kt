package com.example.chris.leafchat.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.chris.leafchat.Logger
import javax.inject.Inject

/**
 * Created by Chris on 2/19/18.
 */
class LandingViewModel @Inject constructor() : ViewModel() {

    fun testValue() {

        Logger.log(LandingViewModel::class.java.name, "clicked")
    }
}
