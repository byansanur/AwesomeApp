package com.byansanur.awesomeapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.byansanur.awesomeapp.api.ApiService
import com.byansanur.awesomeapp.data.paging.PhotoPagingSource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
@Singleton
class RepositoryData @Inject constructor(private val apiService: ApiService) {

    fun getPhotos() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 50,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { PhotoPagingSource(apiService) }
        ).liveData

}