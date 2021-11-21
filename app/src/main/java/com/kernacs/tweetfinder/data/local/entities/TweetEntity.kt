package com.kernacs.tweetfinder.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweets")
data class TweetEntity(
    @PrimaryKey
    val id: String,
    val text: String,
    val createdAt: Long,
    val authorId: String,
    val likeCount: Int,
    val quoteCount: Int,
    val replyCount: Int,
    val retweetCount: Int,
    val authorName: String,
    val authorScreenName: String,
    val authorProfileImageUrl: String,
    val lifeSpanExpirationTimeStamp: Long,
)
