package com.example.android.marsrealestate.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android.marsrealestate.network.MarsProperty

@Dao
interface MarsPropertyDao{
    @Insert
    fun insert(item: MarsPropertyData)

    @Update
    fun update(item: MarsPropertyData)

    @Query("SELECT * from marsproperty_table WHERE imageId = :key")
    fun get(key: Int): MarsPropertyData

    @Query("SELECT * from marsproperty_table")
    fun getAllData(): LiveData<List<MarsPropertyData>>
}
