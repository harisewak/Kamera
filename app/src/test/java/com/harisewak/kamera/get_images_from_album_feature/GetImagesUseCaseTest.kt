package com.harisewak.kamera.get_images_from_album_feature

import com.google.common.truth.Truth.assertThat
import com.harisewak.kamera.data.Image
import com.harisewak.kamera.others.Constants
import com.harisewak.kamera.others.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class GetImagesUseCaseTest {


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `returns empty list if no images are present in the album`() = runBlockingTest {
        val repository = Mockito.mock(GetImagesRepository::class.java)
        val albumId = 1L
        `when`(repository.getImages(albumId)).thenReturn(
            GetImagesResponse.Success(
                images = listOf<Image>()
            )
        )
        val images = GetImagesUseCase(repository).getImages(albumId).images
        assertThat(images).isEmpty()
    }

    @Test
    fun `images are returned when correct albumId is provided`() = runBlockingTest {
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
    fun `fails when invalid albumId is provided`() = runBlockingTest {
        val repository = Mockito.mock(GetImagesRepository::class.java)
        val albumId = -1L
        `when`(repository.getImages(albumId)).thenReturn(GetImagesResponse.Failure(Constants.MSG_INVALID_ALBUM_ID_PROVIDED))
        val response = GetImagesUseCase(repository).getImages(albumId)
        assertThat(response.message).isEqualTo(Constants.MSG_INVALID_ALBUM_ID_PROVIDED)
    }


}