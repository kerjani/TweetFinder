package com.kernacs.tweetfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kernacs.tweetfinder.util.Constants.HILT_INJECTION_TEST
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

@HiltViewModel
class TweetSearchViewModel @Inject constructor(
    @Named(HILT_INJECTION_TEST) val hiltTest: String,
) : ViewModel() {

    fun search() {
        _searchResultItems.postValue(listOf(hiltTest) + List(100) { "${Random.nextInt()}" })
    }

    private var _searchResultItems = MutableLiveData<List<String>?>(null)
    val searchResultItems: LiveData<List<String>?> = _searchResultItems

}