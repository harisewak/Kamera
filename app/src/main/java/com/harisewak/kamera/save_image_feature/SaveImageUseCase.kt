package com.harisewak.kamera.save_image_feature

import com.harisewak.kamera.others.Constants
import com.harisewak.kamera.others.SaveImageResponse
import javax.inject.Inject

/*
* Saving image :-
 - Input -> image
 - Output -> Saved image (success) | error in the process of saving it (failure)
* */

class SaveImageUseCase @Inject constructor(
    val repository: SaveImageRepository
) {


    suspend fun saveImage(imageUri: String): SaveImageResponse {

        if (!imageUri.startsWith(Constants.CONTENT_SCHEMA)) {
            return SaveImageResponse.Failure(Constants.MSG_INVALID_IMAGE_URI)
        }


        return repository.saveImage(imageUri)
    }
}