package com.steamclock.template.dagger.modules

import com.steamclock.template.networking.RedditApi
import com.steamclock.template.persistence.database.databases.AppDatabase
import com.steamclock.template.repositories.RedditPostRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Template
 * Created by jake on 2018-05-23, 3:01 PM
 * Module for app specific objects
 */
@Module(includes = [(NetworkingModule::class), (PersistenceModule::class)])
class AppModule {
    /* Repository for getting posts */
    @Provides
    @Singleton
    fun postRepository(database: AppDatabase, redditApi: RedditApi, executor: Executor): RedditPostRepository {
        return RedditPostRepository(database, redditApi, executor)
    }

    /* A single thread executor */
    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

}