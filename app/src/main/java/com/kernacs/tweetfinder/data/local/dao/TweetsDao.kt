package com.kernacs.tweetfinder.data.local.dao

import androidx.room.*
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TweetsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tweet: TweetEntity)

    @Transaction
    @Query("SELECT * FROM tweets ORDER BY lifeSpanExpirationTimeStamp DESC")
    fun getTweets(): Flow<List<TweetEntity>>

    @Query("DELETE from tweets where lifeSpanExpirationTimeStamp <= :timeStamp")
    suspend fun deleteExpiredData(timeStamp: Long)

    @Query("DELETE FROM tweets")
    suspend fun deleteAll()
}