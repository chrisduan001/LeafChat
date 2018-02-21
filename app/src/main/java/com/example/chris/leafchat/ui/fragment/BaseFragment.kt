package com.example.chris.leafchat.ui.fragment

import android.support.v4.app.Fragment
import android.view.View
import com.example.chris.leafchat.di.HasComponent
import kotlin.reflect.KClass

/**
 * Created by Chris on 2/16/18.
 */

abstract class BaseFragment : Fragment() {

    @Suppress("UNCHECKED_CAST")
    protected fun <T> getComponent(componentType: Class<T>) : T {
        return componentType.cast((activity as HasComponent<T>).getComponent())
    }
}