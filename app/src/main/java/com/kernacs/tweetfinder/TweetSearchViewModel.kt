package com.kernacs.tweetfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kernacs.tweetfinder.data.remote.RemoteDataSource
import com.kernacs.tweetfinder.data.remote.dto.TweetDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TweetSearchViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _error = MutableLiveData<Throwable?>()
    val error: LiveData<Throwable?>
        get() = _error

    private val _searchResultItems = MutableLiveData<List<TweetDto>>(null)
    val searchResultItems: LiveData<List<TweetDto>>
        get() = _searchResultItems

    fun search(query: String) = viewModelScope.launch {
        _isLoading.value = true
        remoteDataSource.search(query) { newItem ->
            runBlocking {
                _isLoading.value = false
                val data: MutableList<TweetDto> =
                    _searchResultItems.value?.toMutableList() ?: mutableListOf()
                data.add(0, newItem)
                _searchResultItems.value = (data)
            }
        }
    }

}