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
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.database.MarsPropertyDao
import com.example.android.marsrealestate.database.MarsPropertyData
import com.example.android.marsrealestate.database.MarsPropertyDatabase
import com.example.android.marsrealestate.network.MarsProperty
import com.squareup.picasso.Picasso
class PhotoGridAdapter(val imageClick: ImageClick) : ListAdapter<MarsPropertyData,MarsPropertyViewHolder>(DataDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        var item = getItem (position)

        holder.itemView.setOnClickListener {
            imageClick.onClick(item)
        }
        holder.bind(item)
    }
}

class DataDiffUtil: DiffUtil.ItemCallback<MarsPropertyData>(){
    override fun areItemsTheSame(oldItem: MarsPropertyData, newItem: MarsPropertyData): Boolean {
        return oldItem.imageId == newItem.imageId
    }

    override fun areContentsTheSame(oldItem: MarsPropertyData, newItem: MarsPropertyData): Boolean {
        return oldItem == newItem
    }
}

class MarsPropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageMars = view.findViewById<ImageView>(R.id.mars_image)

    companion object {
         fun from(parent: ViewGroup): MarsPropertyViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            val view = layoutInflater.inflate(R.layout.grid_view_item, parent, false)

            return MarsPropertyViewHolder(view)
        }
    }

    fun bind(item: MarsPropertyData) {
        Picasso.get().load(item.imageUrl)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(imageMars)
    }
}

class ImageClick(val clickListener: (image: Int) -> Unit){
    fun onClick(item: MarsPropertyData) = clickListener(item.imageId)
}

