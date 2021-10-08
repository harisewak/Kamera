package com.harisewak.kamera.get_images_from_album_feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetImagesViewModel @Inject constructor(
    private val useCase: GetImagesUseCase
) : ViewModel() {

    suspend fun getImages(albumId: Long) = useCase.getImages(albumId)

}