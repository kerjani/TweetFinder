package com.kernacs.tweetfinder

import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import java.util.*

val fakeEntity = TweetEntity(
    id = "",
    text = "",
    createdAt = 0L,
    authorId = "",
    source = "",
    likeCount = 1,
    quoteCount = 2,
    replyCount = 3,
    retweetCount = 4,
    authorName = "",
    authorScreenName = "",
    authorProfileImageUrl = "",
    lifeSpanExpirationTimeStamp = 0L,
)

val expirationReferenceTimeStamp = Date().time

val expiredEntity = fakeEntity.copy(lifeSpanExpirationTimeStamp = expirationReferenceTimeStamp)
val notYetExpiredEntity =
    fakeEntity.copy(lifeSpanExpirationTimeStamp = expirationReferenceTimeStamp + 1 )
