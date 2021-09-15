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

        return try {
            val position = params.key ?: 1
            val response = apiService.getPhotos(position, params.loadSize)
            val prevKey = if (position > 1) position - 1 else null
            val nextKey = if (response.photos.isNotEmpty()) position + 1 else null

            LoadResult.Page(
                data = response.photos,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (ioEx: IOException) {
            LoadResult.Error(ioEx)
        } catch (httpEx: HttpException) {
            LoadResult.Error(httpEx)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoList>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}