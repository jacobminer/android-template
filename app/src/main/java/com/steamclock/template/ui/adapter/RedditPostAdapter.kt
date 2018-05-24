package com.steamclock.template.ui.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.steamclock.template.databinding.ListRedditPostBinding
import com.steamclock.template.models.RedditNewsDataResponse

/**
 * Template
 * Created by jake on 2018-05-23, 2:40 PM
 * The adapter for showing reddit posts using a recyclerview. Uses the Paging library and data binding, so this is mostly just boilerplate.
 * Need to pass a DiffUtil.ItemCallback to the PagedListAdapter, check the definition of RedditNewsDataResponse.DIFF_CALLBACK
 * See: R.layout.list_reddit.post.xml
 */
class RedditPostAdapter : PagedListAdapter<RedditNewsDataResponse, RedditPostAdapter.RedditPostViewHolder>(RedditNewsDataResponse.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostAdapter.RedditPostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        // Uses the itemBinding, specified in the XML. Likely need to sync before this will appear, it's a generated class.
        val itemBinding = ListRedditPostBinding.inflate(layoutInflater, parent, false)
        return RedditPostViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        val post = getItem(position) ?: return
        holder.bind(post)
    }

    /* The recyclerview viewholder. All items are setup in XML via databinding. */
    class RedditPostViewHolder(private val binding: ListRedditPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: RedditNewsDataResponse) {
            /* Binding the item to the viewholder. */
            binding.post = post
            binding.executePendingBindings()
        }
    }
}