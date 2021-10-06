package com.harisewak.kamera.save_image_feature

interface SaveImageRepository {

    suspend fun saveImage(imageUri: String): String

}
