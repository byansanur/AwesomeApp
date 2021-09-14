package com.byansanur.awesomeapp.common

import androidx.recyclerview.widget.DiffUtil
import com.byansanur.awesomeapp.data.model.PhotoList


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
class PhotosUtil {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<PhotoList>() {
            override fun areItemsTheSame(oldItem: PhotoList, newItem: PhotoList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoList, newItem: PhotoList): Boolean =
                oldItem == newItem

        }
    }

}