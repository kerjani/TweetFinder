package com.kernacs.tweetfinder.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddRulesRequestDto(
    val add: List<Rule>
) {
    @Serializable
    data class Rule(
        val tag: String? = null,
        val value: String
    )
}