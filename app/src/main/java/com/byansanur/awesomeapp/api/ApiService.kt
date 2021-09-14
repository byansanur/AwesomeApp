package com.byansanur.awesomeapp.api

import com.byansanur.awesomeapp.data.model.response.RespListPhotos
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
interface ApiService {

    @GET("v1/curated")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : RespListPhotos
}