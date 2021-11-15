package com.kernacs.tweetfinder

import androidx.lifecycle.viewModelScope
import com.kernacs.tweetfinder.base.BaseViewModel
import com.kernacs.tweetfinder.data.remote.RemoteDataSource
import com.kernacs.tweetfinder.data.remote.dto.TweetSearchDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetSearchViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseViewModel<List<TweetSearchDto.Tweet>>() {

    fun search(query: String) = viewModelScope.launch {
        loadData { remoteDataSource.search(query) }
    }

}