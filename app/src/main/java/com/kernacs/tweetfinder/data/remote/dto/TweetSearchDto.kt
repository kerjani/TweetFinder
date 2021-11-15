package com.kernacs.tweetfinder.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TweetSearchDto(
    val statuses: List<Tweet>
) {

    data class Tweet(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("favorite_count")
        val favoriteCount: Int,
        val favorited: Boolean,
        val id: Long,
        @SerializedName("retweet_count")
        val retweetCount: Int,
        val retweeted: Boolean,
        val text: String,
        val user: User
    )

    data class User(
        val name: String,
        @SerializedName("profile_image_url_https")
        val profileImageUrlHttps: String,
        @SerializedName("screen_name")
        val screenName: String,
    )
}
