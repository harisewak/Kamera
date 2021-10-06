package com.harisewak.kamera.save_image_feature

import com.google.common.truth.Truth.assertThat
import com.harisewak.kamera.others.Constants
import com.harisewak.kamera.others.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
class SaveImageUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    /*
    * Test if...
    * ...imageUri is a valid Uri
    * ...image Uri is returned when repository saves image details successfully
    * ...returns error when operation is not successful
    * */

    @Test
    fun `returns error when operation is not successful`() = mainCoroutineRule.runBlockingTest {
        val repository = mock(SaveImageRepository::class.java)
        val imageUri = "content://media/external/images/media/33"
        `when`(repository.saveImage(imageUri)).thenReturn(Constants.MSG_SAVING_IMAGE_FAILED)

        assertThat(SaveImageUseCase(repository).saveImage(imageUri)).isEqualTo(Constants.MSG_SAVING_IMAGE_FAILED)
    }

    @Test
    fun `image Uri is returned when repository saves image details successfully`() =
        mainCoroutineRule.runBlockingTest {
            val validUri = "content://media/external/images/media/33"

            val repository = mock(SaveImageRepository::class.java)
            `when`(repository.saveImage(validUri)).thenReturn(validUri)

            assertThat(SaveImageUseCase(repository).saveImage(validUri)).isEqualTo(validUri)
        }

    @Test
    fun `imageUri is a valid Uri`() = mainCoroutineRule.runBlockingTest {
        val invalidUri = "This is an invalid Uri"
        val repository = mock(SaveImageRepository::class.java)
        assertThat(SaveImageUseCase(repository).saveImage(invalidUri)).isEqualTo(Constants.MSG_INVALID_IMAGE_URI)
    }
}