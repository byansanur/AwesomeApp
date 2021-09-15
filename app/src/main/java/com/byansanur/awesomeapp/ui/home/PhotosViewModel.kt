package com.byansanur.awesomeapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.byansanur.awesomeapp.data.RepositoryData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created by byansanur on 9/14/2021.
 * ratbyansanur81@gmail.com
 */
@HiltViewModel
class PhotosViewModel @Inject constructor(
    repositoryData: RepositoryData
) : ViewModel() {

    val photos = repositoryData.getPhotos().cachedIn(viewModelScope)
}