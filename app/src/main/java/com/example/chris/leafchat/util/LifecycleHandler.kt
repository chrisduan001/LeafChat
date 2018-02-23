package com.example.chris.leafchat.util

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle
import com.example.chris.leafchat.repository.LogoutRepository
import javax.inject.Inject

/**
 * Created by Chris on 2/22/18.
 */
class LifecycleHandler @Inject constructor(
        private val logoutRepository: LogoutRepository,
        private val sharedPreference: LeafSharedPreference)
    :Application.ActivityLifecycleCallbacks, ComponentCallbacks2{

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {}

    override fun onActivityPaused(p0: Activity?) {}

    override fun onActivityDestroyed(p0: Activity?) {}

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}

    override fun onActivityStopped(p0: Activity?) {}

    override fun onLowMemory() {}

    override fun onConfigurationChanged(p0: Configuration?) {}

    override fun onActivityStarted(p0: Activity?) {}

    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            val userName = sharedPreference.getUserName()
            if (!userName.isEmpty()) {
                logoutRepository.logoutUser(userName)
            }
        }
    }

    override fun onActivityResumed(p0: Activity?) {}
}