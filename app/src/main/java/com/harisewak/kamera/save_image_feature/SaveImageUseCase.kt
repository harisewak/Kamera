package com.harisewak.kamera.save_image_feature

import com.harisewak.kamera.others.Constants
import com.harisewak.kamera.others.Failure
import com.harisewak.kamera.others.SaveImageResponse
import com.harisewak.kamera.others.Success

/*
* Saving image :-
 - Input -> image
 - Output -> Saved image (success) | error in the process of saving it (failure)
* */

class SaveImageUseCase(
    val repository: SaveImageRepository
) {


    suspend fun saveImage(imageUri: String): SaveImageResponse {

        if (!imageUri.startsWith(Constants.CONTENT_SCHEMA)) {
            return Failure(Constants.MSG_INVALID_IMAGE_URI)
        }


        return Success(repository.saveImage(imageUri))
    }
}