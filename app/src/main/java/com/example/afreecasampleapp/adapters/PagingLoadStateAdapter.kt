package com.example.afreecasampleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.afreecasampleapp.databinding.PagingFooterBinding

class PagingLoadStateAdapter(private val retry: () -> Unit)
    : LoadStateAdapter<PagingLoadStateAdapter.LoadStateViewHolder>() {


    override fun onBindViewHolder(
        holder: PagingLoadStateAdapter.LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingLoadStateAdapter.LoadStateViewHolder {
        val binding = PagingFooterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: PagingFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState : LoadState){
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}