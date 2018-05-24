package com.steamclock.template.dagger

import com.steamclock.template.App
import com.steamclock.template.dagger.modules.AppModule
import com.steamclock.template.dagger.modules.ContextModule
import com.steamclock.template.dagger.modules.NetworkingModule
import com.steamclock.template.dagger.modules.PersistenceModule
import com.steamclock.template.ui.viewmodels.MainViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Template
 * Created by jake on 2018-05-23, 12:54 PM
 */
@Singleton
@Component(modules = [NetworkingModule::class, PersistenceModule::class, ContextModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(app: MainViewModel)
}