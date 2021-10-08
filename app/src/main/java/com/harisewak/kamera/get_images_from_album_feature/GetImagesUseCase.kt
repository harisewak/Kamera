package com.harisewak.kamera.get_images_from_album_feature

import com.harisewak.kamera.data.Image

class GetImagesUseCase(
    private val repository: GetImagesRepository
) {


    fun getImages(albumId: Int): List<Image> {


        return repository.getImages(albumId)
    }

}