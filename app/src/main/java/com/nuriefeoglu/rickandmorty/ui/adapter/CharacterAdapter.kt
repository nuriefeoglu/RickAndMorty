package com.nuriefeoglu.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.nuriefeoglu.rickandmorty.R
import com.nuriefeoglu.rickandmorty.graphql.GetCharactersQuery
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_character.*

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterVH>() {


    private val charactersData = arrayListOf<GetCharactersQuery.Result?>()

    fun setData(data : List<GetCharactersQuery.Result?>?){
        charactersData.clear()
        data?.let{
            charactersData.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterVH(view)
    }

    override fun getItemCount(): Int {
        return charactersData.size
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        charactersData[position]?.let { holder.bind(it) }
    }


    class CharacterVH(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {


        fun bind(model: GetCharactersQuery.Result) {
            txtCharacterName?.text = model.name

            imgCharacter?.load(model.image) {
                crossfade(true)
            }
        }

        override val containerView: View?
            get() = itemView


    }


}