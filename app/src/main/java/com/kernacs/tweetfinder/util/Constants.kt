package com.kernacs.tweetfinder.util

object Constants {
    const val BASE_API_V1_URL = "https://api.twitter.com/"
    const val V1_SEARCH_ENDPOINT = "1.1/search/tweets.json"
    const val SEARCH_QUERY = "q"
    const val COUNT_QUERY = "count"

    const val STREAMING_SEARCH_URL = "https://stream.twitter.com/1.1/statuses/filter.json"
    const val BASE_STREAM_URL = "https://stream.twitter.com/"
    const val STREAM_FILTER_ENDPOINT = "1.1/statuses/filter.json"
    const val TRACK_QUERY = "track"
    const val STALL_WARNINGS_QUERY = "stall_warnings"

    const val SEARCH_RESULT_COUNT = 50
}