package com.example.chris.leafchat.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Chris on 2/20/18.
 */
@Singleton
class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards AndroidViewModel>)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?:
        creators.asIterable().firstOrNull {modelClass.isAssignableFrom(it.key)}?.value
        ?: throw IllegalArgumentException("unknown model class " + modelClass)

        return try {
            creator as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}