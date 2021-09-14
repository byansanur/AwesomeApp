package com.byansanur.awesomeapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.common.PhotosUtil.Companion.DIFF_UTIL
import com.byansanur.awesomeapp.data.model.PhotoList
import com.byansanur.awesomeapp.databinding.ItemPhotoGridBinding


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
class PhotosAdapterGrid(private val listener: OnItemClickListener) :
    PagingDataAdapter<PhotoList, PhotosAdapterGrid.Holder>(DIFF_UTIL){

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val bindings = ItemPhotoGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(bindings)
    }

    inner class Holder(private val binding: ItemPhotoGridBinding) :
            RecyclerView.ViewHolder(binding.root) {
                init {
                    binding.root.setOnClickListener {
                        val pos = bindingAdapterPosition
                        if (pos != RecyclerView.NO_POSITION) {
                            val item = getItem(pos)
                            if (item != null)
                                listener.onItemClick(item)
                        }
                    }
                }

                fun bind(photo: PhotoList) {
                    binding.apply {
                        Glide.with(itemView)
                            .load(photo.src.portrait)
                            .fitCenter()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                            .error(R.drawable.ic_baseline_broken_image_24)
                            .into(imgThumb)

                        tvDescription.text = photo.photographer
                    }
                }
            }

    interface OnItemClickListener {
        fun onItemClick(photo: PhotoList)
    }

//    companion object {
//        private val DIFF_UTILS = object : DiffUtil.ItemCallback<PhotoList>() {
//            override fun areItemsTheSame(oldItem: PhotoList, newItem: PhotoList): Boolean =
//                oldItem.id == newItem.id
//
//            override fun areContentsTheSame(oldItem: PhotoList, newItem: PhotoList): Boolean =
//                oldItem == newItem
//
//        }
//    }
}