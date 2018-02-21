package com.example.chris.leafchat.di.modules

import android.os.Build
import com.example.chris.leafchat.BuildConfig
import com.example.chris.leafchat.network.LeafChatApi
import com.example.chris.leafchat.network.ServiceHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Chris on 2/21/18.
 */
@Module
class NetworkModule {

    @Provides @Singleton
    fun provideNetworkHelper(retrofit: Retrofit) : ServiceHelper {
        val chatApi = retrofit.create(LeafChatApi::class.java)
        return ServiceHelper(chatApi)
    }

    @Provides @Singleton
    fun provideRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit
                .Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}