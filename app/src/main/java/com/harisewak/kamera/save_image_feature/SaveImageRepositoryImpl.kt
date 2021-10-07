package com.harisewak.kamera.save_image_feature

import android.util.Log
import com.harisewak.kamera.data.Album
import com.harisewak.kamera.data.AlbumDao
import com.harisewak.kamera.data.Image
import com.harisewak.kamera.data.ImageDao
import com.harisewak.kamera.others.Constants
import com.harisewak.kamera.others.Failure
import com.harisewak.kamera.others.SaveImageResponse
import com.harisewak.kamera.others.Success
import java.lang.Exception
import javax.inject.Inject

const val TAG = "SaveImageRepo"

class SaveImageRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val imageDao: ImageDao
) : SaveImageRepository {
    private var albumId = -1L

    override suspend fun saveImage(imageUri: String): SaveImageResponse {
        return try {
            if (albumId == -1L) {
                // create a new album and save image in it
                val newAlbum = Album(
                    firstImageUri = imageUri
                )
                albumId = albumDao.insert(newAlbum)
                val newImage = Image(
                    imageUri = imageUri,
                    albumId = albumId
                )
                imageDao.insert(newImage)
            } else {
                // save imageUri in existing album
                val newImage = Image(
                    imageUri = imageUri,
                    albumId = albumId
                )
                imageDao.insert(newImage)
            }

            return Success(imageUri)
        } catch (error: Exception) {
            Log.d(TAG, "saveImage: ${error.localizedMessage}")
            Failure(Constants.MSG_SAVING_IMAGE_FAILED)
        }
    }

}
