package com.harisewak.kamera.get_images_from_album_feature

import com.harisewak.kamera.data.Image

sealed class GetImagesResponse(
    open val images: List<Image> = listOf(),
    open val message: String? = null
) {
    class Success(override val images: List<Image>) : GetImagesResponse(images = images)
    class Failure(override val message: String) : GetImagesResponse(message = message)
}
