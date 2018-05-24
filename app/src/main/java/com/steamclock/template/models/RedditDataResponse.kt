package com.steamclock.template.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.v7.util.DiffUtil
import com.squareup.moshi.JsonClass

/**
 * Template
 * Created by jake on 2018-05-23, 2:05 PM
 * Serves as both the JSON model that returns from the API and the Database entry we pull from
 * @JsonClass(generateAdapter = true) is used by Moshi so we don't need to create our own JSON mappings
 */
@JsonClass(generateAdapter = true)
data class RedditNewsResponse(val data: RedditDataResponse)

@JsonClass(generateAdapter = true)
data class RedditDataResponse(val children: List<RedditChildrenResponse>,
                              val after: String?,
                              val before: String?)

@JsonClass(generateAdapter = true)
data class RedditChildrenResponse(val data: RedditNewsDataResponse)

/**
 *
 */
@JsonClass(generateAdapter = true)
@Entity(tableName = "redditPosts")
data class RedditNewsDataResponse(
        @PrimaryKey var id: String,
        @ColumnInfo(name = "author") val author: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "created") val created: Long,
        @ColumnInfo(name = "thumbnail") val thumbnail: String,
        @ColumnInfo(name = "url") val url: String,
        @ColumnInfo(name = "name") val name: String
) {
    // Seems to be necessary when using Room and Kotlin
    @Ignore constructor(): this("", "", "", 0, "", "", "")

    companion object {
        // Used with the Paging library, very useful for RecyclerViews
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditNewsDataResponse>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldConcert: RedditNewsDataResponse,
                                         newConcert: RedditNewsDataResponse): Boolean =
                    oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: RedditNewsDataResponse,
                                            newConcert: RedditNewsDataResponse): Boolean =
                    oldConcert == newConcert
        }
    }
}