package com.example.chris.leafchat.di.components

import com.example.chris.leafchat.di.PerActivity
import com.example.chris.leafchat.di.modules.LoginActivityModule
import com.example.chris.leafchat.ui.fragment.LoginFragment
import dagger.Component

/**
 * Created by Chris on 2/15/18.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [LoginActivityModule::class])
interface LoginComponent {
    fun inject(fragment: LoginFragment)
}