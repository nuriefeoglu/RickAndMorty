package com.nuriefeoglu.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nuriefeoglu.rickandmorty.graphql.type.FilterCharacter
import com.nuriefeoglu.rickandmorty.graphql.type.FilterEpisode
import com.nuriefeoglu.rickandmorty.graphql.type.FilterLocation

class MainViewModel : ViewModel() {

    val charactersFilter = MutableLiveData<FilterCharacter>()
    val episodesFilter = MutableLiveData<FilterEpisode>()
    val locationsFilter = MutableLiveData<FilterLocation>()
}

