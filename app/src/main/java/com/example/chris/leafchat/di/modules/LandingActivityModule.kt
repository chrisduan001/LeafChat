package com.example.chris.leafchat.di.modules

import android.app.Activity
import com.example.chris.leafchat.TestPresenter
import com.example.chris.leafchat.di.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by Chris on 2/15/18.
 */
@Module
class LandingActivityModule constructor(private val activity: Activity) {

    @Provides @PerActivity
    fun provideActivity() : Activity {
        return activity
    }
}