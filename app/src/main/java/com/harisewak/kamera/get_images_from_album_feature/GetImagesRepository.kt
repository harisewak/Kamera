package com.harisewak.kamera.get_images_from_album_feature

import com.harisewak.kamera.data.Image

interface GetImagesRepository {

    fun getImages(albumId: Int): List<Image>

}