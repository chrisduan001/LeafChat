package com.example.chris.leafchat.di.modules

import android.app.Application
import android.content.Context
import com.example.chris.leafchat.LeafApplication
import com.example.chris.leafchat.util.ErrorHandler
import com.example.chris.leafchat.util.LeafSharedPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Chris on 2/15/18.
 */
@Module(includes = [ViewModelModule::class])
class ApplicationModule(private val application: LeafApplication) {
    private val sp_name = "com.leafchat"

    @Provides @Singleton
    fun provideApplicationContext(): Application { return application }

    @Provides @Singleton
    fun provideErrorHandler() : ErrorHandler { return ErrorHandler() }

    @Provides @Singleton
    fun provideSharedPreference() : LeafSharedPreference {
        val sp = application.getSharedPreferences(sp_name, Context.MODE_PRIVATE)
        return  LeafSharedPreference(sp)
    }

}