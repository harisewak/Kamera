package com.harisewak.kamera.save_image_feature

import com.harisewak.kamera.data.AlbumDao
import com.harisewak.kamera.data.ImageDao
import com.harisewak.kamera.save_image_feature.SaveImageRepository
import com.harisewak.kamera.save_image_feature.SaveImageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(
    ViewModelComponent::class
)
object SaveImageModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(
        albumDao: AlbumDao,
        imageDao: ImageDao
    ): SaveImageRepository = SaveImageRepositoryImpl(
        albumDao, imageDao
    )


}