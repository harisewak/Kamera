package com.harisewak.kamera.save_image_feature

class FakeSaveImageRepository : SaveImageRepository {
    override suspend fun saveImage(imageUri: String): String {
        return imageUri
    }
}