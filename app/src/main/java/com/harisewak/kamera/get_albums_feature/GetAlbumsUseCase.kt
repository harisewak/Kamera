package com.harisewak.kamera.get_albums_feature

import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: GetAlbumsRepository
) {

    suspend fun getAlbums() = repository.getAlbums()

}