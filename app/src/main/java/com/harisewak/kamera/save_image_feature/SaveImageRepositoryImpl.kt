package com.harisewak.kamera.save_image_feature

import android.util.Log
import com.harisewak.kamera.data.Album
import com.harisewak.kamera.data.AlbumDao
import com.harisewak.kamera.data.Image
import com.harisewak.kamera.data.ImageDao
import com.harisewak.kamera.others.Constants
import com.harisewak.kamera.others.SaveImageResponse
import javax.inject.Inject

class SaveImageRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val imageDao: ImageDao
) : SaveImageRepository {
    private var albumId = -1L
    private val TAG = "SaveImageRepo"
    private lateinit var curImage: Image

    override suspend fun saveImage(imageUri: String): SaveImageResponse {
        return try {
            if (albumId == -1L) {
                // create a new album and save image in it
                val newAlbum = Album(
                    firstImageUri = imageUri
                )
                albumId = albumDao.insert(newAlbum)
                curImage = Image(
                    imageUri = imageUri,
                    albumId = albumId
                )
                imageDao.insert(curImage)
            } else {
                // save imageUri in existing album
                curImage = Image(
                    imageUri = imageUri,
                    albumId = albumId
                )
                imageDao.insert(curImage)
            }

            return SaveImageResponse.Success(imageUri)
        } catch (error: Exception) {
            Log.d(TAG, "saveImage: ${error.localizedMessage}")
            SaveImageResponse.Failure(Constants.MSG_SAVING_IMAGE_FAILED)
        }
    }

    override fun getPreviewImage() = curImage

}
