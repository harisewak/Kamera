package com.harisewak.kamera.get_albums_feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAlbumsViewModel @Inject constructor(
    private val useCase: GetAlbumsUseCase
) : ViewModel() {
    private val _albums = MutableLiveData<GetAlbumsResponse>()
    val albums = _albums

    init {
        getAlbums()
    }

    fun getAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            _albums.postValue(useCase.getAlbums())
        }
    }

}