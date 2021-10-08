package com.harisewak.kamera.get_images_from_album_feature

import com.google.common.truth.Truth.assertThat
import com.harisewak.kamera.data.Image
import com.harisewak.kamera.others.Constants
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class GetImagesUseCaseTest {
    /* Test if...
    * ...images are returned when correct albumId is provided
    * ...fails when invalid albumId is provided
    * ...returns empty list if no images are present in the album */

    @Test
    fun `images are returned when correct albumId is provided`() {
        val repository = Mockito.mock(GetImagesRepository::class.java)
        val albumId = 1L
        `when`(repository.getImages(albumId)).thenReturn(
            GetImagesResponse.Success(
                images = listOf<Image>(
                    Image(1, "content://valid_image_uri", albumId)
                )
            )
        )
        val images = GetImagesUseCase(repository).getImages(albumId).images
        assertThat(images).isNotEmpty()
    }

    @Test
    fun `fails when invalid albumId is provided`() {
        val repository = Mockito.mock(GetImagesRepository::class.java)
        val albumId = -1L
        `when`(repository.getImages(albumId)).thenReturn(GetImagesResponse.Failure(Constants.MSG_INVALID_ALBUM_ID_PROVIDED))
        val response = GetImagesUseCase(repository).getImages(albumId)
        assertThat(response.message).isEqualTo(Constants.MSG_INVALID_ALBUM_ID_PROVIDED)
    }


}