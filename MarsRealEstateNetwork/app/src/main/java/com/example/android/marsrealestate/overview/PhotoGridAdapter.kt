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

package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.network.MarsProperty
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_overview.view.*
import kotlinx.android.synthetic.main.grid_view_item.view.*

class PhotoGridAdapter : RecyclerView.Adapter<MarsPropertyViewHolder>() {
    var data = listOf<MarsProperty>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.grid_view_item,parent,false)

        return MarsPropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val item = data[position]
        //holder.imageMars.setImageURI(item.imgSrcUrl.toUri())
        Picasso.get().load(item.imgSrcUrl).into(holder.imageMars)
    }

}
class MarsPropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageMars = view.findViewById<ImageView>(R.id.mars_image)
}

