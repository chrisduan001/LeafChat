package com.example.chris.leafchat.di.modules

import android.app.Application
import android.content.Context
import com.example.chris.leafchat.LeafApplication
import com.example.chris.leafchat.util.ErrorHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Chris on 2/15/18.
 */
@Module(includes = [ViewModelModule::class])
class ApplicationModule(private val application: LeafApplication) {

    @Provides @Singleton
    fun provideApplicationContext(): Application { return application }

    @Provides @Singleton
    fun provideErrorHandler() : ErrorHandler { return ErrorHandler() }
}