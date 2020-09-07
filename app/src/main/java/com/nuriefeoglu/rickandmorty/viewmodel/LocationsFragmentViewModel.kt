package com.nuriefeoglu.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Input
import com.nuriefeoglu.rickandmorty.graphql.GetLocationsQuery
import com.nuriefeoglu.rickandmorty.networking.Networking
import com.nuriefeoglu.rickandmorty.networking.Status.*

class LocationsFragmentViewModel : BaseViewModel() {

    private var page: Int = 1
    val locationData = MutableLiveData<List<GetLocationsQuery.Result?>>()

    init {
        getAllLocations()
    }

    private fun getAllLocations() {
        Networking.request(GetLocationsQuery(Input.fromNullable(page))) {
            when (it.status) {
                SUCCESS -> {
                    locationData.postValue(it.data?.locations?.results)
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