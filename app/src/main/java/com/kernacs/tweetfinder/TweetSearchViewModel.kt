package com.kernacs.tweetfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class TweetSearchViewModel : ViewModel() {

    fun search() {
        _searchResultItems.postValue(List(100) { "${Random.nextInt()}" })
    }

    private var _searchResultItems = MutableLiveData<List<String>?>(null)
    val searchResultItems: LiveData<List<String>?> = _searchResultItems

}