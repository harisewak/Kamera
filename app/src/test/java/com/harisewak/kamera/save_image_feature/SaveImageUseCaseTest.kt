package com.harisewak.kamera.save_image_feature

import com.google.common.truth.Truth.assertThat
import com.harisewak.kamera.others.Constants
import com.harisewak.kamera.others.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveImageUseCaseTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository = FakeSaveImageRepository()



    /*
    * Test if...
    * ...imageUri is a valid Uri
    * ...image Uri is returned when repository returns success
    * ...throws error when operation is not successful
    * */

    @Test
    fun `succeeds when repository returns saved uri`() = mainCoroutineRule.runBlockingTest {
        val validUri = "content://media/external/images/media/33"
        assertThat(SaveImageUseCase(repository).saveImage(validUri)).isEqualTo(validUri)
    }

    @Test
    fun `fails if imageUri is invalid`() = mainCoroutineRule.runBlockingTest {
        val invalidUri = "This is an invalid Uri"
        assertThat(SaveImageUseCase(repository).saveImage(invalidUri)).isEqualTo(Constants.MSG_INVALID_IMAGE_URI)
    }
}