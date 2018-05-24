package com.steamclock.template

import android.app.Application
import com.steamclock.template.dagger.ApplicationComponent
import com.steamclock.template.dagger.DaggerApplicationComponent
import com.steamclock.template.dagger.modules.ContextModule
import com.steamclock.template.dagger.modules.PersistenceModule

/**
 * Template
 * Created by jake on 2018-05-23, 12:56 PM
 * The application class. Handles setting up Dagger
 * Provides a static way of accessing the App context
 */
class App: Application() {
    lateinit var component: ApplicationComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        App.instance = this
        component = DaggerApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .persistenceModule(PersistenceModule(this))
                .build()
    }
}
