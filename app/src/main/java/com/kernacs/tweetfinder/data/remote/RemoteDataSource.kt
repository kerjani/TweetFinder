package com.kernacs.tweetfinder.data.remote

import com.kernacs.tweetfinder.data.remote.dto.TweetDto
import io.ktor.utils.io.*

interface RemoteDataSource {
    suspend fun search(
        term: String,
        onStreamStarted: suspend (ByteReadChannel) -> Unit,
        onNewTweet: suspend (TweetDto) -> Unit
    )
}