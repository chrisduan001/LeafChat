package com.example.chris.leafchat.di.components

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.chris.leafchat.di.PerActivity
import com.example.chris.leafchat.di.modules.LandingActivityModule
import com.example.chris.leafchat.di.modules.ViewModelModule
import com.example.chris.leafchat.ui.fragment.LandingFragment
import dagger.Component

/**
 * Created by Chris on 2/15/18.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [LandingActivityModule::class])
interface LandingComponent {
    fun inject(fragment: LandingFragment)

//    fun activity(): Activity
}