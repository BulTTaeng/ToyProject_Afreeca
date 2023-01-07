package com.example.afreecasampleapp.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afreecasampleapp.ViewPagerHomeFragmentDirections
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.databinding.ItemBroadViewBinding

class BroadPagingAdapter : PagingDataAdapter<Broad, BroadPagingAdapter.BroadViewHolder>(BroadDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BroadViewHolder {
        return BroadViewHolder(
            ItemBroadViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BroadViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class BroadViewHolder(
        private val binding: ItemBroadViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Broad) {
            binding.apply {
                broad = item
                executePendingBindings()
            }

            itemView.setOnClickListener{
                val navController = itemView.findNavController()
                navController.navigate(ViewPagerHomeFragmentDirections.actionViewPagerHomeFragmentToDetailFragment(item))
            }
        }
    }
}

private class BroadDiffCallback : DiffUtil.ItemCallback<Broad>() {
    override fun areItemsTheSame(oldItem: Broad, newItem: Broad): Boolean {
        return oldItem.user_id == newItem.user_id
    }

    override fun areContentsTheSame(oldItem: Broad, newItem: Broad): Boolean {
        return oldItem == newItem
    }
}