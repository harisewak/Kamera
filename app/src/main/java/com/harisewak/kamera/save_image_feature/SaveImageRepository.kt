package com.harisewak.kamera.save_image_feature

import com.harisewak.kamera.data.Image
import com.harisewak.kamera.others.SaveImageResponse

interface SaveImageRepository {

    suspend fun saveImage(imageUri: String): SaveImageResponse
    fun getPreviewImage(): Image

}
