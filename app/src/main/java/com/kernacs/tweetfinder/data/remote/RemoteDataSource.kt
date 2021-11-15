package com.kernacs.tweetfinder.data.remote

import com.kernacs.tweetfinder.data.remote.dto.TweetSearchDto
import com.kernacs.tweetfinder.util.Result

interface RemoteDataSource {
    suspend fun search(term: String): Result<List<TweetSearchDto.Tweet>>
    suspend fun streamTweets(terms: String, callback: (String) -> Unit)
}