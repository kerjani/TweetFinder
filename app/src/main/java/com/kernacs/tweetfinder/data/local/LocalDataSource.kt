package com.kernacs.tweetfinder.data.local

import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getTweets(): Flow<List<TweetEntity>>
    suspend fun saveTweet(tweet: TweetEntity)
    suspend fun deleteAllTweets()
    suspend fun deleteExpiredData(timeStamp: Long)
    suspend fun insertAndCleanup(timeStamp: Long, tweet: TweetEntity)
}