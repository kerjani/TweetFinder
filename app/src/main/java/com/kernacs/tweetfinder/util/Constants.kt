package com.kernacs.tweetfinder.util

object Constants {
    const val V2_SEARCH_STREAM_ENDPOINT = "2/tweets/search/stream"
    const val V2_SEARCH_RULES_ENDPOINT = "2/tweets/search/stream/rules"
    private const val BASE_API_URL = "https://api.twitter.com/"

    fun String.withBaseUrl() = BASE_API_URL + this

    const val CONFIGURATION_TAG = "ConfigurationCheck"
    const val DEFAULT_CONFIG_LIFESPAN = 30000L
}