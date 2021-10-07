package com.harisewak.kamera.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {

    @Query("SELECT * FROM album")
    suspend fun getAll(): List<Album>

    @Query(
        """SELECT * 
                FROM album
                WHERE id=:id LIMIT 1"""
    )
    suspend fun getAlbum(id: Int): Album

    @Insert
    suspend fun insert(album: Album): Long

}