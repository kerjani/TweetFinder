package com.kernacs.tweetfinder.data.remote

import com.kernacs.tweetfinder.data.remote.dto.TweetDto

interface RemoteDataSource {
    suspend fun search(term: String, onNewTweet: (TweetDto) -> Unit)
}