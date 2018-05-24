package com.steamclock.template.persistence.database.databases

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.steamclock.template.models.RedditNewsDataResponse
import com.steamclock.template.persistence.database.doas.RedditDao


/**
 * Template
 * Created by jake on 2018-05-23, 1:24 PM
 * The database holding the DAO, linking to the entities
 * App will crash if you change entities and don't bump version
 * Make sure a data migration pattern is setup in the PersistenceModule or else migration will crash.
 * Note: When migrating versions right now, it seems to always fail to write the first time. See https://github.com/googlesamples/android-architecture-components/issues/276.
 */
@Database(entities = [(RedditNewsDataResponse::class)], version = 9)
abstract class AppDatabase : RoomDatabase() {
    abstract fun redditDao(): RedditDao
}