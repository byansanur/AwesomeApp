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
import com.byansanur.awesomeapp.databinding.ItemPhotoLinearBinding


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
class PhotosAdapterLinear(private val listener: OnItemClickListenerLinear) :
    PagingDataAdapter<PhotoList, PhotosAdapterLinear.LinearHolder>(DIFF_UTIL) {


    inner class LinearHolder(private val binding: ItemPhotoLinearBinding) :
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
                        .centerInside()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(imgThumb)

                    tvDescription.text = photo.photographer
                }
            }
    }

    override fun onBindViewHolder(holder: LinearHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearHolder {
        val binding = ItemPhotoLinearBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LinearHolder(binding)
    }



    interface OnItemClickListenerLinear {
        fun onItemClick(photo: PhotoList)
    }


}