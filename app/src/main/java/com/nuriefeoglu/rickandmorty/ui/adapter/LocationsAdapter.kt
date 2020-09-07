package com.nuriefeoglu.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nuriefeoglu.rickandmorty.R
import com.nuriefeoglu.rickandmorty.graphql.GetLocationsQuery
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_locations.*

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.LocationsVH>() {


    private val locationsData = arrayListOf<GetLocationsQuery.Result?>()

    fun setData(data: List<GetLocationsQuery.Result?>?){
        locationsData.clear()
        data?.let {
            locationsData.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_locations, parent , false)
        return LocationsVH(view)
    }

    override fun getItemCount(): Int {
        return locationsData.size
    }

    override fun onBindViewHolder(holder: LocationsVH, position: Int) {
        locationsData[position]?.let {
            holder.bind(it)
        }
    }


    class LocationsVH(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        fun bind(model: GetLocationsQuery.Result) {

            txtLocationsName?.text = model.name

            txtDimentions?.text = model.dimension

        }

        override val containerView: View?
            get() = itemView

    }

}