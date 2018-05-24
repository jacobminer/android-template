package com.steamclock.template.dagger.modules

import android.content.Context
import com.steamclock.template.App
import dagger.Module
import javax.inject.Singleton
import dagger.Provides

/**
 * Template
 * Created by jake on 2018-05-23, 1:12 PM
 * Module for providing main application and application context
 */
@Module
class ContextModule(val app: App) {
    @Provides
    @Singleton
    fun mainApplication(): App {
        return app
    }

    @Provides
    @Singleton
    fun applicationContext(): Context {
        return app
    }
}