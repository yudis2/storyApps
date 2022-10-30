package com.yudisdwi.submission1.auth.main.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yudisdwi.submission1.R
import com.yudisdwi.submission1.databinding.ItemListStoryBinding
import com.yudisdwi.submission1.model.StoryModel

class StoryAdapter : PagingDataAdapter<StoryModel, StoryAdapter.ListStoryViewHolder>(mDiffCallback) {

    inner class ListStoryViewHolder(private val binding: ItemListStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoryModel){
            binding.apply {
                tvUsername.text = story.name
                tvDesc.text = story.description
                Glide.with(itemView.context)
                    .load(story.photoUrl)
                    .fitCenter()
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_baseline_refresh_24)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    )
                    .into(ivStory)
            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListStoryViewHolder {
        val binding = ItemListStoryBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ListStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListStoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val mDiffCallback = object : DiffUtil.ItemCallback<StoryModel>() {
            override fun areItemsTheSame(oldItem: StoryModel, newItem: StoryModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StoryModel,
                newItem: StoryModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
