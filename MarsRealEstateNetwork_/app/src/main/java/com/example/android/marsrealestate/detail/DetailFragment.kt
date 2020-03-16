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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.database.MarsPropertyDao
import com.example.android.marsrealestate.database.MarsPropertyData
import com.example.android.marsrealestate.database.MarsPropertyDatabase
import com.example.android.marsrealestate.network.MarsProperty
import com.squareup.picasso.Picasso

/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    private lateinit var iv_image: ImageView
    private lateinit var tv_type: TextView
    private lateinit var tv_price: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_detail, container, false)

        iv_image = view.findViewById(R.id.main_photo_image)
        tv_price = view.findViewById(R.id.price_value_text)
        tv_type = view.findViewById(R.id.property_type_text)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        val marsProperty = DetailFragmentArgs.fromBundle(arguments!!).imageID

        viewModel._id.value = marsProperty


        viewModel.thisItem.observe(this, Observer { item->
            if (item.imageId == marsProperty){
                updateView(item)
            }

        })
        return view
    }

    private fun updateView(item:MarsPropertyData) {
        Picasso.get()
                .load(item.imageUrl)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(iv_image)
        tv_type.text = item.imageType
        tv_price.text = item.imagePrice.toString()
    }
}