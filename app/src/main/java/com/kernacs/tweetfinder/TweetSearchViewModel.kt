package com.kernacs.tweetfinder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kernacs.tweetfinder.data.remote.RemoteDataSource
import com.kernacs.tweetfinder.data.remote.dto.TweetDto
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.statement.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TweetSearchViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    sealed class ViewState {
        object OnBoarding : ViewState()
        object Loading : ViewState()
        object WaitingForStream : ViewState()
        data class Error(val exception: Throwable?) : ViewState()
        data class Data(val searchResult: List<TweetDto>) : ViewState()
    }

    private val _viewState = MutableLiveData<ViewState>(ViewState.OnBoarding)
    val viewState = _viewState

    private var runningSearch: HttpResponse? = null

    fun cancelSearch() = runningSearch?.apply {
        call.cancel()
    }

    fun search(query: String) = viewModelScope.launch {
        Log.d(TAG, "Data loading has been started")
        _viewState.value = ViewState.Loading
        cancelSearch()
        remoteDataSource.search(query, { byteChannel ->
            runningSearch = byteChannel
            _viewState.value = ViewState.WaitingForStream
            Log.d(TAG, "Waiting for the search result to be returned")
        }, { newItem ->
            runBlocking {
                val data: MutableList<TweetDto> = if (_viewState.value is ViewState.Data)
                    (_viewState.value as ViewState.Data).searchResult.toMutableList()
                else
                    mutableListOf()
                data.add(0, newItem)
                _viewState.value = ViewState.Data(data)
            }
        })
    }

    companion object {
        const val TAG = "TweetSearchViewModel"
    }

}