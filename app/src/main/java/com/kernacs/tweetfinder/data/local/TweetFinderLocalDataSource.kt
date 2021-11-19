package com.kernacs.tweetfinder.data.local

import com.kernacs.tweetfinder.data.local.dao.TweetsDao
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TweetFinderLocalDataSource @Inject constructor(
    private val dao: TweetsDao
) : LocalDataSource {
    override suspend fun deleteExpiredData(timeStamp: Long) = dao.deleteExpiredData(timeStamp)
    override suspend fun insertAndCleanup(timeStamp: Long, tweet: TweetEntity) =
        dao.insertAndCleanup(timeStamp, tweet)

    override fun getTweets(): Flow<List<TweetEntity>> = dao.getTweets()

    override suspend fun saveTweet(tweet: TweetEntity) = dao.insert(tweet)

    override suspend fun deleteAllTweets() = dao.deleteAll()
}