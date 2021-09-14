package com.byansanur.awesomeapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.byansanur.awesomeapp.api.ApiService
import com.byansanur.awesomeapp.common.START_PAGING
import com.byansanur.awesomeapp.data.model.PhotoList
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
class PhotoPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, PhotoList>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoList> {
        val position = params.key ?: START_PAGING

        return try {
            val response = apiService.getPhotos(position, params.loadSize)
            val photoList = response.photos

            LoadResult.Page(
                data = photoList,
                prevKey = if (position == START_PAGING) null else position -1,
                nextKey = if (photoList.isEmpty()) null else position + 1
            )
        } catch (ioEx: IOException) {
            LoadResult.Error(ioEx)
        } catch (httpEx: HttpException) {
            LoadResult.Error(httpEx)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoList>): Int? {
        return null
    }

}