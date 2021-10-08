package com.harisewak.kamera.get_images_from_album_feature

import com.google.common.truth.Truth.assertThat
import com.harisewak.kamera.data.Image
import com.harisewak.kamera.save_image_feature.SaveImageRepository
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
        val albumId = 1
        `when`(repository.getImages(albumId)).thenReturn(listOf<Image>(
            Image(1, "content://valid_image_uri", albumId.toLong())
        ))
        val images = GetImagesUseCase(repository).getImages(albumId)
        assertThat(images).isNotEmpty()
    }


}