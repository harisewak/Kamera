package com.harisewak.kamera.get_images_from_album_feature

import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: GetImagesRepository
) {

    suspend fun getImages(albumId: Long) = repository.getImages(albumId)

}