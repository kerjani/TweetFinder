package com.kernacs.tweetfinder.data.remote

import com.kernacs.tweetfinder.data.remote.dto.TweetDto
import io.ktor.client.statement.*

interface RemoteDataSource {
    suspend fun search(
        term: String,
        onStreamStarted: (HttpResponse) -> Unit,
        onNewTweet: (TweetDto) -> Unit
    )
}