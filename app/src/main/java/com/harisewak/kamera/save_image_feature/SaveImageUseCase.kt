package com.harisewak.kamera.save_image_feature

import android.net.Uri
import com.harisewak.kamera.others.Constants

/*
* Saving image :-
 - Input -> image
 - Output -> Saved image (success) | error in the process of saving it (failure)
* */

class SaveImageUseCase(
    val repository: SaveImageRepository
) {


    suspend fun saveImage(imageUri: String): String {
        if (!imageUri.startsWith(Constants.CONTENT_SCHEMA)) {
            return Constants.MSG_INVALID_IMAGE_URI
        }


        return repository.saveImage(imageUri)
    }
}