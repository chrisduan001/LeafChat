package com.example.chris.leafchat

import android.app.Application
import com.example.chris.leafchat.di.components.ApplicationComponent
import com.example.chris.leafchat.di.components.DaggerApplicationComponent
import com.example.chris.leafchat.di.modules.ApplicationModule

/**
 * Created by Chris on 2/15/18.
 */
class LeafApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}