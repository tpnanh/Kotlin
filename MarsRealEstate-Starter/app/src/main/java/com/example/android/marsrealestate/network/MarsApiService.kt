/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)//cai nay hong hieu, cái này là dựa trên cái API của em, ví dụ em muốn vô gg thì em gõ google.com còn này em muốn đọc 
        //API thief em parhi chuyền cho nó cái url để nó biết lấy API ở dâu okie
        .build()

object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) }
}

interface MarsApiService{
        @GET("realestate")//nếu đúng á là "https://android-kotlin-fun-mars-server.appspot.com/release  mà ok
        //mà em tưởng tượng nếu em truyền vô cái dòng này ở base url thì khi mấy lấy tham số khác thì phải làm sao :)
        //nên baseURL sẽ là url chính còn muốn lấy gì thì thêm ở sau như muốn lấy cái release chẵng hạn hieu oi :)) ổnchuwa chưa anh đá em được chưa :))
        //là do em truyền cái đuôi không có nên nó null sodi :)) ok
        fun getProperties():
                Deferred<List<MarsProperty>>
}