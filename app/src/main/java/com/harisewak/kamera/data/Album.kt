package com.harisewak.kamera.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @PrimaryKey(
        autoGenerate = true
    )
    val id: Long? = null,
    val firstImageUri: String
)