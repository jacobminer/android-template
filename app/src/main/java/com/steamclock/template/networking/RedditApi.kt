package com.steamclock.template.networking

import com.steamclock.template.models.RedditNewsResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Template
 * Created by jake on 2018-05-23, 2:02 PM
 * Simple reddit API service.
 */
interface RedditApi {
    /* Returns the top X posts (up to "limit") starting from the post with the name "after" */
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String): Deferred<Response<RedditNewsResponse>>
}