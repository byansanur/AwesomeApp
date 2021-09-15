package com.byansanur.awesomeapp.ui.home.adapter

import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
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
class PhotosAdapterGrid (private val listener: OnItemClickListener) :
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

                        val textHtml = "hi, i'm <b>${photo.photographer}<b>. <a href=\"${photo.photographerUrl}\">Follow</a> " +
                                "and <a href=\"${photo.url}\">Like my photo</a> on paxels"

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            tvDescription.text = Html.fromHtml(textHtml, Html.FROM_HTML_MODE_LEGACY)
                        } else tvDescription.text = Html.fromHtml(textHtml)
                        tvDescription.movementMethod = LinkMovementMethod.getInstance()
                    }
                }
            }
}