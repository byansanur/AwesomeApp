package com.byansanur.awesomeapp.data.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.byansanur.awesomeapp.api.ApiService
import com.byansanur.awesomeapp.data.fake.FakeDataPhotos
import com.byansanur.awesomeapp.data.model.PhotoList
import com.byansanur.awesomeapp.data.model.response.RespListPhotos
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

/**
 * Created by byansanur on 9/16/2021.
 * ratbyansanur81@gmail.com
 */
@ExperimentalCoroutinesApi
class PhotoPagingSourceTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    val coroutineTestRule = CoroutineR()

    @Mock lateinit var api: ApiService

    lateinit var photoPagingSource: PhotoPagingSource

    private lateinit var respListPhotos: RespListPhotos

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        photoPagingSource = PhotoPagingSource(api)

        respListPhotos = FakeDataPhotos.generateFakeDataRespPhotos()
    }


    @Test
    fun `photo paging source load - failure - http error`() = runBlockingTest {
        val error = RuntimeException("404", Throwable())
        given(api.getPhotos(0, 0)).willThrow(error)

        val expectedResult = PagingSource.LoadResult.Error<Int, PhotoList>(error)

        assertEquals(
            expectedResult, photoPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = true
                )
            )
        )

    }

    @Test
    fun `photo paging source load - failure - received null`() = runBlockingTest {
        given(api.getPhotos(1, 1)).willReturn(null)
        val expectedResult = PagingSource.LoadResult.Error<Int, PhotoList>(NullPointerException())
        assertEquals(
            expectedResult.toString(), photoPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ).toString()
        )
    }

    @Test
    fun `photo paging source refresh - success`() = runBlockingTest {
        given(api.getPhotos(1, 2)).willReturn(respListPhotos)
        val expectedResult = PagingSource.LoadResult.Page(
            data = respListPhotos.photos,
            prevKey = null,
            nextKey = 1
        )
        assertEquals(
            expectedResult, photoPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}