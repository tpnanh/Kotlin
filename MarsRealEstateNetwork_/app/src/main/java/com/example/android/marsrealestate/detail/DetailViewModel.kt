/*
 *  Copyright 2019, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.android.marsrealestate.detail

import android.app.Application
import android.media.Image
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.database.MarsPropertyDao
import com.example.android.marsrealestate.database.MarsPropertyData
import com.example.android.marsrealestate.database.MarsPropertyDatabase
import com.example.android.marsrealestate.detail.DetailFragment
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsApiService
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.*


class DetailViewModel(app: Application) : AndroidViewModel(app) {
    var _id = MutableLiveData<Int>()
    val id : LiveData<Int>
        get() = _id

    var dataSource : MarsPropertyDao = MarsPropertyDatabase.getInstance(app).marsPropertyDao
    val database = dataSource

    private var _thisItem = MutableLiveData<MarsPropertyData>()
    val thisItem : LiveData<MarsPropertyData>
    get() = _thisItem

    private var viewModelJob = Job()
    private val dataScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        dataScope.launch {
            var temp : MarsPropertyData = MarsPropertyData(0,"","",0F)
            withContext(Dispatchers.IO){
                if(id.value!=null){
                    temp = database.get(id.value!!)

                }
            }
            _thisItem.value = temp
        }


    }
}
