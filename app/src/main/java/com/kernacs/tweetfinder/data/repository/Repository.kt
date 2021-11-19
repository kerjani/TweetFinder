package com.kernacs.tweetfinder.data.repository

import com.kernacs.tweetfinder.TweetSearchViewModel
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

    suspend fun searchTweets(term: String): Flow<TweetSearchViewModel.ViewState>
    fun getTweets(): Flow<List<TweetEntity>>
    suspend fun deleteExpiredTweets(timeStamp: Long)
    suspend fun deleteTweets()
    fun cancelSearch()
    suspend fun insertAndCleanup(timeStamp: Long, tweet: TweetEntity)
}