package com.nuriefeoglu.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nuriefeoglu.rickandmorty.R
import com.nuriefeoglu.rickandmorty.graphql.GetEpisodesQuery
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_episodes.*

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodesVH>() {

    private val episodesData = arrayListOf<GetEpisodesQuery.Result?>()

    fun setData(data: List<GetEpisodesQuery.Result?>?){
        episodesData.clear()
        data?.let {
            episodesData.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episodes, parent, false)
        return EpisodesVH(view)
    }

    override fun getItemCount(): Int {
        return episodesData.size
    }

    override fun onBindViewHolder(holder: EpisodesVH, position: Int) {
        episodesData[position]?.let {
            holder.bind(it)
        }
    }


    class EpisodesVH(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        fun bind(model: GetEpisodesQuery.Result) {

            txtEpisodesName?.text = model.name

            txtEpisodesInfo?.text = model.episode

        }

        override val containerView: View?
            get() = itemView

    }

}