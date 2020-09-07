package com.nuriefeoglu.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val loadingOutput = MutableLiveData<Boolean>()
    val errorOutput = MutableLiveData<String?>()

    override fun onCleared() {
        loadingOutput.postValue(false)
        super.onCleared()
    }

}