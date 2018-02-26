package com.example.chris.leafchat

import com.example.chris.leafchat.di.DaggerMainTestComponent
import com.example.chris.leafchat.di.MainTestComponent
import com.example.chris.leafchat.di.modules.ApplicationModule
import com.example.chris.leafchat.util.LeafSharedPreference
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by Chris on 2/23/18.
 */
@RunWith(RobolectricTestRunner::class)
open class BaseTest {
    protected val component: MainTestComponent

    init {
        val application = RuntimeEnvironment.application as LeafApplication

        component = DaggerMainTestComponent.builder()
                .applicationModule(ApplicationModule(application))
                .build()
    }
}