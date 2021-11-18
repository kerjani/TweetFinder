package com.kernacs.tweetfinder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TweetsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tweet: TweetEntity)

    @Query("SELECT * FROM tweets ORDER BY lifeSpanExpirationTimeStamp DESC")
    fun getTweets(): Flow<List<TweetEntity>>

    @Query("DELETE from tweets where lifeSpanExpirationTimeStamp <= :timeStamp")
    suspend fun deleteExpiredData(timeStamp: Long = Date().time)

    @Query("DELETE FROM tweets")
    suspend fun deleteAll()
}