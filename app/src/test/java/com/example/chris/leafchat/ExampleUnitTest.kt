package com.example.chris.leafchat

import com.example.chris.leafchat.ui.fragment.BaseFragment
import com.example.chris.leafchat.ui.fragment.LandingFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    sealed class TestEnum {
        object val1: TestEnum()
        object val2: TestEnum()
    }

    @Test
    fun addition_isCorrect() {

    }
}



