package com.nuriefeoglu.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Input
import com.nuriefeoglu.rickandmorty.graphql.GetCharactersQuery
import com.nuriefeoglu.rickandmorty.graphql.type.FilterCharacter
import com.nuriefeoglu.rickandmorty.networking.Networking
import com.nuriefeoglu.rickandmorty.networking.Status.*

class CharactersFragmentViewModel : BaseViewModel() {


    private var page: Int = 1
    val charactersData = MutableLiveData<List<GetCharactersQuery.Result?>>()

    init {
        getAllCharacters()
    }


    fun getAllCharacters(filter: FilterCharacter? = null) {
        Networking.request(GetCharactersQuery(Input.fromNullable(page), Input.fromNullable(filter))) {
            when (it.status) {
                SUCCESS -> {
                    charactersData.postValue(it.data?.characters?.results)
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