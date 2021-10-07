package com.harisewak.kamera.save_image_feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveImageViewModel @Inject constructor(
    val useCase: SaveImageUseCase
) : ViewModel() {

    suspend fun saveImage(imageUri: String) = useCase.saveImage(imageUri)
}