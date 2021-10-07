package com.harisewak.kamera.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val imageUri: String,
    val albumId: Long // is a foreign key (maps to id from Album table)
)