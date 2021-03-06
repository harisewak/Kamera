package com.harisewak.kamera.get_images_from_album_feature

import com.harisewak.kamera.data.Image

interface GetImagesRepository {

    suspend fun getImages(albumId: Long): GetImagesResponse

}