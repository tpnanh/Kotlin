package com.example.android.marsrealestate.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marsproperty_table")
data class MarsPropertyData(
        @PrimaryKey(autoGenerate = true)
        var imageId: Int,

        @ColumnInfo(name = "image_url")
        var imageUrl: String = "",

        @ColumnInfo(name = "image_type")
        var imageType: String = "",

        @ColumnInfo(name = "image_price")
        var imagePrice: Float = 0F
)
