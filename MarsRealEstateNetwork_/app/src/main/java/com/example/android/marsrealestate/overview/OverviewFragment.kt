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

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.R

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class OverviewFragment : Fragment() {

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    lateinit var adapter: PhotoGridAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_overview, container, false)

        var viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)
        // Giving the binding access to the OverviewViewModel

        adapter = PhotoGridAdapter(ImageClick {itImage ->
            viewModel.onClickImage(itImage)
        })

        var photoGridView = view.findViewById<RecyclerView>(R.id.photos_grid)

        photoGridView.layoutManager = GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false)
        photoGridView.adapter = adapter

        viewModel.itemDatas.observe(viewLifecycleOwner, Observer {it ->
                adapter.submitList(it)
            }
        )

        viewModel.navigateToDetail.observe(this, Observer { it ->
            it?.let {
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                viewModel.onDoneClickImage()
            }

        })

        setHasOptionsMenu(true)
        return view
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
