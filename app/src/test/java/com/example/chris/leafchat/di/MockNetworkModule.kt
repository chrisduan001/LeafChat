package com.example.chris.leafchat.di

import com.example.chris.leafchat.mocks.MockServiceHelper
import com.example.chris.leafchat.network.LeafChatApi
import com.example.chris.leafchat.network.ServiceHelper
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Created by Chris on 2/26/18.
 */
@Module
class MockNetworkModule {

    @Provides @Singleton
    fun provideServiceHelper() : ServiceHelper {
        return MockServiceHelper(Mockito.mock(LeafChatApi::class.java))
    }
}