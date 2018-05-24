package com.steamclock.template.persistence.database.doas

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.steamclock.template.models.RedditNewsDataResponse

/**
 * Template
 * Created by jake on 2018-05-23, 2:24 PM
 * Interface for interacting with the Reddit posts database
 */
@Dao
interface RedditDao {
    /* Using DataSource.Factory for the Paging library. If you don't need paging, LiveData will likely suffice */
    @get:Query("SELECT * FROM redditPosts")
    val all: DataSource.Factory<Int, RedditNewsDataResponse>

    /* Method for inserting into database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: RedditNewsDataResponse)

    @Delete
    fun delete(post: RedditNewsDataResponse)
}