package com.example.chris.leafchat.di.components

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.chris.leafchat.TestPresenter
import com.example.chris.leafchat.ui.activity.BaseActivity
import com.example.chris.leafchat.di.modules.ApplicationModule
import com.example.chris.leafchat.di.modules.ViewModelModule
import com.example.chris.leafchat.ui.activity.LandingActivity
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Singleton

/**
 * Created by Chris on 2/15/18.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(activity: BaseActivity)

    fun factory(): ViewModelProvider.Factory
    fun application() : Application
}