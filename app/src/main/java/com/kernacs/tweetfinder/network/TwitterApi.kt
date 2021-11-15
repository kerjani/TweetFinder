package com.kernacs.tweetfinder.network

import com.kernacs.tweetfinder.data.remote.dto.TweetSearchDto
import com.kernacs.tweetfinder.util.Constants.COUNT_QUERY
import com.kernacs.tweetfinder.util.Constants.SEARCH_QUERY
import com.kernacs.tweetfinder.util.Constants.SEARCH_RESULT_COUNT
import com.kernacs.tweetfinder.util.Constants.STALL_WARNINGS_QUERY
import com.kernacs.tweetfinder.util.Constants.STREAMING_SEARCH_URL
import com.kernacs.tweetfinder.util.Constants.TRACK_QUERY
import com.kernacs.tweetfinder.util.Constants.V1_SEARCH_ENDPOINT
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Streaming


interface TwitterApi {
    @GET(V1_SEARCH_ENDPOINT)
    suspend fun search(
        @Query(SEARCH_QUERY) terms: String,
        @Query(COUNT_QUERY) count: Int = SEARCH_RESULT_COUNT
    ): Response<TweetSearchDto>

    @Streaming
    @POST(STREAMING_SEARCH_URL)
    fun searchTweetsByTerm(
        @Query(TRACK_QUERY) terms: String,
        @Query(STALL_WARNINGS_QUERY) stallWarnings: Boolean = true
    ): Call<ResponseBody>
}