package com.harisewak.kamera.get_albums_feature

import com.harisewak.kamera.data.AlbumDao
import com.harisewak.kamera.data.ImageDao
import com.harisewak.kamera.get_images_from_album_feature.GetImagesRepository
import com.harisewak.kamera.get_images_from_album_feature.GetImagesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(
    ViewModelComponent::class
)
object GetAlbumsModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(
        albumDao: AlbumDao
    ): GetAlbumsRepository = GetAlbumsRepositoryImpl(
        albumDao
    )


}