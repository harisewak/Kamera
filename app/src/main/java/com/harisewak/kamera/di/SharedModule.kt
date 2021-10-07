package com.harisewak.kamera.di

import android.content.Context
import com.harisewak.kamera.data.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(
    SingletonComponent::class
)
object SharedModule {

    @Provides
    @Singleton
    fun provideAlbumDao(
        @ApplicationContext context: Context
    ) = LocalDatabase.getInstance(context)
        .albumDao()

    @Provides
    @Singleton
    fun provideImageDao(
        @ApplicationContext context: Context
    ) = LocalDatabase.getInstance(context)
        .imageDao()

}