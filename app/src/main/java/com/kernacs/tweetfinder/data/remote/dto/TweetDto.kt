package com.kernacs.tweetfinder.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TweetDto(
    val `data`: Data,
    val includes: Includes,
    @SerialName("matching_rules")
    val matchingRules: List<MatchingRule>,
) {
    @Serializable
    data class Data(
        val id: String,
        val text: String,
        @SerialName("created_at")
        val createdAt: String,
        @SerialName("author_id")
        val authorId: String,
        @SerialName("public_metrics")
        val publicMetrics: PublicMetrics,
        val source: String
    ) {
        @Serializable
        data class PublicMetrics(
            @SerialName("like_count")
            val likeCount: Int,
            @SerialName("quote_count")
            val quoteCount: Int,
            @SerialName("reply_count")
            val replyCount: Int,
            @SerialName("retweet_count")
            val retweetCount: Int
        )
    }

    @Serializable
    data class Includes(
        val users: List<User>
    ) {
        @Serializable
        data class User(
            val id: String,
            val name: String,
            val username: String,
            @SerialName("profile_image_url")
            val profileImageUrl: String
        )
    }

    @Serializable
    data class MatchingRule(
        val id: String, // 1459899541357047812
        val tag: String? = null
    )
}