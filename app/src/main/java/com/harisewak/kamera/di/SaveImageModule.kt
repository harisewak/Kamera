package com.harisewak.kamera.di

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
    fun provideRepository(): SaveImageRepository = SaveImageRepositoryImpl()


}