package com.example.chris.leafchat.di.components

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.example.chris.leafchat.LeafApplication
import com.example.chris.leafchat.ui.activity.BaseActivity
import com.example.chris.leafchat.di.modules.ApplicationModule
import com.example.chris.leafchat.di.modules.NetworkModule
import com.example.chris.leafchat.network.ServiceHelper
import com.example.chris.leafchat.ui.activity.LandingActivity
import com.example.chris.leafchat.util.ErrorHandler
import com.example.chris.leafchat.util.LeafSharedPreference
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chris on 2/15/18.
 */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(activity: BaseActivity)
    fun inject(application: LeafApplication)

    fun factory() : ViewModelProvider.Factory
    fun application() : Application
    fun serviceHelper() : ServiceHelper
    fun errorHandler() : ErrorHandler
    fun sharedPreference() : LeafSharedPreference
}