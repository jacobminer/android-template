package com.steamclock.template.ui.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.steamclock.template.App
import com.steamclock.template.models.RedditNewsDataResponse
import com.steamclock.template.repositories.RedditPostRepository
import javax.inject.Inject

/* ViewModel for the MainFragment. Build one of these for each screen, unless screens are very similar. */
/* Injects the RedditPostRepository via dagger, and provides a LiveData for the fragment to monitor for posts. */
class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel. This should be where most of the business logic lives
    // Fetch data using a Repository or Manager class. Listen for changes to the Repository using LiveData
    // The fragment should then observe the liveData from this class

    @Inject
    lateinit var repository: RedditPostRepository

    var posts: LiveData<PagedList<RedditNewsDataResponse>>

    init {
        App.instance.component.inject(this)
        posts = repository.getPosts()
    }
}
