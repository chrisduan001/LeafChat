package com.example.chris.leafchat

import android.app.Application
import com.example.chris.leafchat.di.components.ApplicationComponent
import com.example.chris.leafchat.di.components.DaggerApplicationComponent
import com.example.chris.leafchat.di.modules.ApplicationModule
import com.example.chris.leafchat.util.LifecycleHandler
import javax.inject.Inject

/**
 * Created by Chris on 2/15/18.
 */
class LeafApplication : Application() {
    @Inject lateinit var lifecycleHandler: LifecycleHandler

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent.inject(this)

        registerActivityLifecycleCallbacks(lifecycleHandler)
        registerComponentCallbacks(lifecycleHandler)
    }
}