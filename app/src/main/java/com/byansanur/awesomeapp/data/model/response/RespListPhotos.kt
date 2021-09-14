package com.byansanur.awesomeapp.data.model.response
import com.byansanur.awesomeapp.data.model.PhotoList
import com.google.gson.annotations.SerializedName



/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
data class RespListPhotos(
    @SerializedName("photos")
    val photos: List<PhotoList>
)