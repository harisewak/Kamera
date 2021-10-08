package com.harisewak.kamera.get_albums_feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harisewak.kamera.data.Album
import com.harisewak.kamera.get_images_from_album_feature.GetImagesUseCase
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

    private fun getAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            _albums.postValue(useCase.getAlbums())
        }
    }

}