package com.harisewak.kamera.save_image_feature

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
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

    /*
    * Test if...
    * ...imageUri is a valid Uri
    * ...image Uri is returned when repository returns success
    * ...throws error when operation is not successful
    * */

    @Test
    fun `fail if imageUri is invalid`() = mainCoroutineRule.runBlockingTest {
        val testUri = "This is an invalid Uri"
        assertThat(SaveImageUseCase().saveImage(testUri)).isEqualTo(Constants.MSG_INVALID_IMAGE_URI)
    }
}