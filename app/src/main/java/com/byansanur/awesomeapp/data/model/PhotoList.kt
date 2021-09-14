package com.byansanur.awesomeapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
@Parcelize
data class PhotoList(
    @SerializedName("avg_color")
    val avgColor: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("photographer")
    val photographer: String,
    @SerializedName("photographer_id")
    val photographerId: Int,
    @SerializedName("photographer_url")
    val photographerUrl: String,
    @SerializedName("src")
    val src: SourcePhoto,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
) : Parcelable {

    @Parcelize
    data class SourcePhoto(
        @SerializedName("landscape")
        val landscape: String,
        @SerializedName("large")
        val large: String,
        @SerializedName("large2x")
        val large2x: String,
        @SerializedName("medium")
        val medium: String,
        @SerializedName("original")
        val original: String,
        @SerializedName("portrait")
        val portrait: String,
        @SerializedName("small")
        val small: String,
        @SerializedName("tiny")
        val tiny: String
    ) : Parcelable
    
}