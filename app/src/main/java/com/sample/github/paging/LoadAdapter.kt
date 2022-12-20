package com.sample.github.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.github.databinding.LoaderItemBinding

class LoadAdapter: LoadStateAdapter<LoadAdapter.LoaderViewHolder> () {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LoaderItemBinding.inflate(layoutInflater, parent, false)
        return LoaderViewHolder(binding)
    }


    class LoaderViewHolder(var binding: LoaderItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState) {
           binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.executePendingBindings()
        }
    }


}


