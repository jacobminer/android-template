package com.steamclock.template.dagger.modules

import android.arch.persistence.room.Room
import com.steamclock.template.App
import com.steamclock.template.persistence.database.databases.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Template
 * Created by jake on 2018-05-23, 12:55 PM
 * Module for building and providing the Room Database (or any other persistence we'd like)
 */
@Module
class PersistenceModule(val app: App) {
    @Provides
    @Singleton
    fun database(): AppDatabase {
        // TODO - Change the database name to something more useful
        return Room.databaseBuilder(app, AppDatabase::class.java, "database-name")
                // TODO - Add proper migration handling. Using fallbackToDestructiveMigration() will just drop the old database
                .fallbackToDestructiveMigration()
//                optionally add a callback for when the database is created
//                .addCallback(object : RoomDatabase.Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        super.onCreate(db)
//                    }
//
//                    override fun onOpen(db: SupportSQLiteDatabase) {
//                        super.onOpen(db)
//                    }
//                })
                .build()
    }
}