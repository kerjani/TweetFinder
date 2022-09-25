package com.kernacs.tweetfinder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import com.kernacs.tweetfinder.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetSearchViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    sealed class ViewState {
        object OnBoarding : ViewState()
        object Loading : ViewState()
        object WaitingForStream : ViewState()
        data class Error(val exception: Throwable?) : ViewState()
        object SearchResult : ViewState()
    }

    private val _viewState = MutableLiveData<ViewState>(ViewState.OnBoarding)
    val viewState = _viewState

    val searchResult: Flow<List<TweetEntity>> = repository.getTweets()

    private fun cancelSearch() = repository.cancelSearch()

    fun search(query: String) = viewModelScope.launch {
        cancelSearch()
        repository.searchTweets(query).collect {
            _viewState.value = it
        }
    }

    companion object {
        const val TAG = "TweetSearchViewModel"
    }

}