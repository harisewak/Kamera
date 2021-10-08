package com.harisewak.kamera.get_images_from_album_feature

import android.util.Log
import com.harisewak.kamera.data.ImageDao
import com.harisewak.kamera.others.Constants
import javax.inject.Inject


class GetImagesRepositoryImpl @Inject constructor(
    private val imageDao: ImageDao
) : GetImagesRepository {
    private val TAG = "GetImagesRepo"

    override suspend fun getImages(albumId: Long): GetImagesResponse {
        return try {
            val images = imageDao.getAll(albumId)
            GetImagesResponse.Success(images = images)
        } catch (error: Exception) {
            Log.d(TAG, "getImages: ${error.localizedMessage}")
            GetImagesResponse.Failure(Constants.MSG_ALBUM_LOADING_FAILED)
        }
    }
}