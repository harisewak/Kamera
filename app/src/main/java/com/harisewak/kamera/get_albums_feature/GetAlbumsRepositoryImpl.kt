package com.harisewak.kamera.get_albums_feature

import android.util.Log
import com.harisewak.kamera.data.AlbumDao
import com.harisewak.kamera.data.ImageDao
import com.harisewak.kamera.get_images_from_album_feature.GetImagesRepository
import com.harisewak.kamera.get_images_from_album_feature.GetImagesResponse
import com.harisewak.kamera.others.Constants
import javax.inject.Inject


class GetAlbumsRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao
) : GetAlbumsRepository {
    private val TAG = "GetAlbumsRepo"

    override suspend fun getAlbums(): GetAlbumsResponse {
        return try {
            val albums = albumDao.getAll()
            GetAlbumsResponse.Success(albums = albums)
        } catch (error: Exception) {
            Log.d(TAG, "getAlbums: ${error.localizedMessage}")
            GetAlbumsResponse.Failure(Constants.MSG_LOADING_ALBUMS_FAILED)
        }
    }
}