package com.kernacs.tweetfinder.data.repository

import com.kernacs.tweetfinder.TweetSearchViewModel
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun searchTweets(term: String): Flow<TweetSearchViewModel.ViewState>
    fun getTweets(): Flow<List<TweetEntity>>
    suspend fun deleteExpiredTweets()
    suspend fun deleteTweets()
    fun cancelSearch()
}