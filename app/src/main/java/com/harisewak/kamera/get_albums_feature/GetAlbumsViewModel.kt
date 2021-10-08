package com.harisewak.kamera.get_albums_feature

import androidx.lifecycle.ViewModel
import com.harisewak.kamera.get_images_from_album_feature.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetAlbumsViewModel @Inject constructor(
    private val useCase: GetAlbumsUseCase
) : ViewModel() {

    suspend fun getAlbums() = useCase.getAlbums()

}