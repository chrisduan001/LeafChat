package com.example.chris.leafchat.di.modules

import android.content.Context
import com.example.chris.leafchat.LeafApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Chris on 2/15/18.
 */
@Module(includes = [ViewModelModule::class])
class ApplicationModule(private val application: LeafApplication) {

    @Provides @Singleton
    fun provideApplicationContext(): Context { return application }
}