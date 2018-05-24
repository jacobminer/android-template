package com.steamclock.template.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.steamclock.template.databinding.MainFragmentBinding
import com.steamclock.template.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import android.support.v7.widget.LinearLayoutManager
import com.steamclock.template.ui.adapter.RedditPostAdapter

/* Main fragment
* Uses databinding where possible, follows the viewmodel pattern. Only logic related to views should be done here!
* See: R.layout.main_fragment
* Views are used directly using Kotlin Android Extensions. */
class MainFragment : Fragment() {
    // Fetch the viewmodel for this screen.
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    /* Sets up the viewbinding and associates the viewmodel lifecycle to this fragment */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    /* Called when we're ready to setup the view model. All observing should be done here! */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel to update the UI. Best done using LiveData.observe. Make the viewModel observe events if necessary
        // Refer to this resource for passing data:
        // https://developer.android.com/topic/libraries/architecture/navigation/navigation-implementing#Safe-args
        // Note that the dependency should already be setup
        val context = context ?: return

        // Sets up the recyclerview to observe changes to the viewModel.posts. That's all we need!
        recyclerView.layoutManager = LinearLayoutManager(context)
        val postsAdapter = RedditPostAdapter()
        viewModel.posts.observe(this, Observer { pagedList ->
            postsAdapter.submitList(pagedList)
        })
        recyclerView.adapter = postsAdapter
    }

}
