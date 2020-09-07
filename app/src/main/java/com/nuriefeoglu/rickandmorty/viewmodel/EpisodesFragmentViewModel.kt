package com.nuriefeoglu.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Input
import com.nuriefeoglu.rickandmorty.graphql.GetEpisodesQuery
import com.nuriefeoglu.rickandmorty.graphql.type.FilterEpisode
import com.nuriefeoglu.rickandmorty.networking.Networking
import com.nuriefeoglu.rickandmorty.networking.Status.*

class EpisodesFragmentViewModel : BaseViewModel() {

    private var page: Int = 1

    val episodesData = MutableLiveData<List<GetEpisodesQuery.Result?>>()


    init {
        getAllEpisodes()
    }


    fun getAllEpisodes(filter: FilterEpisode? = null) {
        Networking.request(GetEpisodesQuery(Input.fromNullable(page), Input.fromNullable(filter))) {
            when (it.status) {
                SUCCESS -> {
                    episodesData.postValue(it.data?.episodes?.results)
                    loadingOutput.postValue(false)
                }
                FAIL -> {
                    errorOutput.postValue(it.errorMessage)
                    loadingOutput.postValue(false)
                }
                LOADING -> loadingOutput.postValue(true)

            }
        }
    }
}