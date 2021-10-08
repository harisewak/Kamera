package com.harisewak.kamera.get_albums_feature

import com.harisewak.kamera.data.Album
import com.harisewak.kamera.data.Image

sealed class GetAlbumsResponse(
    open val albums: List<Album> = listOf(),
    open val message: String? = null
) {
    class Success(override val albums: List<Album>) : GetAlbumsResponse(albums = albums)
    class Failure(override val message: String) : GetAlbumsResponse(message = message)
}
