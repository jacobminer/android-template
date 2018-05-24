package com.steamclock.template.repositories

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.steamclock.template.models.RedditNewsDataResponse
import com.steamclock.template.networking.RedditApi
import com.steamclock.template.persistence.database.databases.AppDatabase
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.Executor
import javax.inject.Singleton

/**
 * Template
 * Created by jake on 2018-05-23, 2:59 PM
 * A repository for accessing data. Takes care of downloading data from the API into the database,
 * then serves data from the database as a single point of truth.
 * Needs an executor in order to write to the database without crashing UI thread. (Might be able to replace with WorkManager?)
 * ViewModels should only ever have to access this, and should never need to access redditApi or AppDatabase directly.
 */
@Singleton
class RedditPostRepository(val database: AppDatabase, private val redditApi: RedditApi, private val executor: Executor) {
    /* Gets posts from the redditAPI and writes them to the database.
     * Uses a BoundaryCallback so we can page using the Paging library. */
    fun getPosts(): LiveData<PagedList<RedditNewsDataResponse>> {
        val boundaryCallback = RedditPostsBoundaryCallback(
                redditApi = redditApi,
                handleResponse = this::addToDB)

        // create a data source factory from Room
        val dataSourceFactory = database.redditDao().all
        return LivePagedListBuilder(dataSourceFactory, 10).setBoundaryCallback(boundaryCallback).build()
    }

    // writes items to the database.
    private fun addToDB(data: Array<RedditNewsDataResponse>) {
        executor.execute {
            database.redditDao().insertAll(*data)
        }
    }
}

/* Handles all the paging. Reddit uses linked-list style paging, so we pass the last item's name as the "after" parameter in this case.
 * When we first open the app, no items are loaded, so we ping the API.
 * Once we hit the last item, the API will automatically go to fetch more. */
class RedditPostsBoundaryCallback(private val redditApi: RedditApi,
                                  private val handleResponse: (Array<RedditNewsDataResponse>) -> Unit): PagedList.BoundaryCallback<RedditNewsDataResponse>() {

    override fun onZeroItemsLoaded() {
        refreshPosts("")
    }

    override fun onItemAtEndLoaded(itemAtEnd: RedditNewsDataResponse) {
        refreshPosts(itemAtEnd.name)
    }

    /* Using coroutines while fetching data from the redditApi */
    private fun refreshPosts(after: String) = launch(UI) {
        val response = redditApi.getTop(after, "30").await()
        if (response.isSuccessful && response.body() != null) {
            val data = response.body()?.data?.children?.map { it.data } ?: return@launch
            handleResponse(data.toTypedArray())
        }
    }
}