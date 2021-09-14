package com.byansanur.awesomeapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.byansanur.awesomeapp.databinding.ItemPhotoStateFooterBinding


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
class PhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotoLoadStateAdapter.LoadStateHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateHolder {
        val binding = ItemPhotoStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateHolder(private val binding: ItemPhotoStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.buttonRetry.setOnClickListener { retry.invoke() }
            }
            fun bind(loadState: LoadState) {
                binding.apply {
                    progressBar.isVisible = loadState is LoadState.Loading
                    buttonRetry.isVisible = loadState !is LoadState.Loading
                    textViewError.isVisible = loadState !is LoadState.Loading
                }
            }
        }




}