package com.harisewak.kamera.get_albums_feature

import com.harisewak.kamera.get_images_from_album_feature.GetImagesResponse

interface GetAlbumsRepository {

    suspend fun getAlbums(): GetAlbumsResponse

}