package com.steamclock.template.dagger.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.steamclock.template.networking.RedditApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Template
 * Created by jake on 2018-05-23, 12:55 PM
 * Module for building and using networking components
 */
@Module
class NetworkingModule {
    /* Moshi adapter is used for JSON -> Object conversion */
    @Provides
    @Singleton
    fun moshi(): Moshi {
        return Moshi.Builder()
                // ... add your own JsonAdapters and factories ...
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    /* Retrofit handles networking. We're initializing it with Moshi adapters, and enabling coroutine support
     * adding the okHttpClient is optional, but it allows us to easily add interceptors */
    @Provides
    @Singleton
    fun retrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                // TODO: make this use correct url
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    /* okHttpClient allows us to modify some of the lower level networking. */
    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                // add interceptors here
                .build()
    }

    /* The API we're using via retrofit. */
    @Provides
    @Singleton
    fun redditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }
}