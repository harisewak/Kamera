package com.harisewak.kamera.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageDao {

    @Query(
        """SELECT *
                FROM image
                where albumId=:albumId
                """)
    suspend fun getAll(albumId: Int): List<Image>

    @Insert
    suspend fun insert(image: Image)

}