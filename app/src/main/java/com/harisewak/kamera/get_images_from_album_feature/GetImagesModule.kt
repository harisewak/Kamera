package com.harisewak.kamera.get_images_from_album_feature

import com.harisewak.kamera.data.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(
    ViewModelComponent::class
)
object GetImagesModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(
        imageDao: ImageDao
    ): GetImagesRepository = GetImagesRepositoryImpl(
        imageDao
    )


}