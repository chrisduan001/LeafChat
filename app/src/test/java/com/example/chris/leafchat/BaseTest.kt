package com.example.chris.leafchat

import android.support.annotation.CallSuper
import com.example.chris.leafchat.di.DaggerMainTestComponent
import com.example.chris.leafchat.di.MainTestComponent
import com.example.chris.leafchat.di.modules.ApplicationModule
import com.example.chris.leafchat.ui.activity.ChatRoomActivity
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by Chris on 2/23/18.
 */
@RunWith(RobolectricTestRunner::class)
open class BaseTest {
    protected val component: MainTestComponent
    protected val application: LeafApplication

    init {
        application = RuntimeEnvironment.application as LeafApplication

        component = DaggerMainTestComponent.builder()
                .applicationModule(ApplicationModule(application))
                .build()
    }

    @Before
    @CallSuper
    open fun before() {
        RxJavaPlugins.setIoSchedulerHandler{ _ -> Schedulers.trampoline() }
    }

    @After
    fun after() {
        RxAndroidPlugins.reset()
    }
}