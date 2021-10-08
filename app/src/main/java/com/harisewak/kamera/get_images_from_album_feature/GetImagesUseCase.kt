package com.harisewak.kamera.get_images_from_album_feature

import com.harisewak.kamera.data.Image

class GetImagesUseCase(
    private val repository: GetImagesRepository
) {

    suspend fun getImages(albumId: Long) = repository.getImages(albumId)

}