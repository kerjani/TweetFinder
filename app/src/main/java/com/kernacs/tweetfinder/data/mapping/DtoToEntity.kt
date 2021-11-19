package com.kernacs.tweetfinder.data.mapping

import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import com.kernacs.tweetfinder.data.remote.dto.TweetDto
import com.kernacs.tweetfinder.util.expirationTimeStamp
import com.kernacs.tweetfinder.util.isoStringToDate
import java.util.*

fun TweetDto.toEntity(): TweetEntity {
    return TweetEntity(
        id = data.id,
        text = data.text,
        createdAt = data.createdAt.isoStringToDate().time,
        authorId = data.authorId,
        source = data.source,
        likeCount = data.publicMetrics.likeCount,
        replyCount = data.publicMetrics.replyCount,
        quoteCount = data.publicMetrics.quoteCount,
        retweetCount = data.publicMetrics.retweetCount,
        authorName = includes.users[0].name,
        authorScreenName = includes.users[0].username,
        authorProfileImageUrl = includes.users[0].profileImageUrl,
        lifeSpanExpirationTimeStamp = Date().expirationTimeStamp()
    )
}