package com.example.android.marsrealestate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MarsPropertyData::class], version = 1, exportSchema = false)
abstract class MarsPropertyDatabase : RoomDatabase(){
    abstract val marsPropertyDao: MarsPropertyDao

    companion object{
        @Volatile
        private var INSTANCE : MarsPropertyDatabase?=null

        fun getInstance(context: Context): MarsPropertyDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance==null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            MarsPropertyDatabase::class.java,
                            "marsproperty_history_data"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return instance
            }
        }

    }
}