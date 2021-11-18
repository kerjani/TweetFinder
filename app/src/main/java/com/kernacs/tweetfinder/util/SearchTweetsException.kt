package com.kernacs.tweetfinder.util

import com.kernacs.tweetfinder.data.local.entities.TweetEntity

class SearchTweetsException(
    val exception: Exception,
    val onSearchTerm: String,
    val previousSearchResult: List<TweetEntity>
) : Exception() {
}