package com.kernacs.tweetfinder.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetRulesResponseDto(
    val `data`: List<Data>? = null,
    val meta: Meta?? = null,
    val errors: List<String>? = null,
) {
    @Serializable
    data class Data(
        val id: String,
        val tag: String? = null,
        val value: String
    )

    @Serializable
    data class Meta(
        val sent: String
    )
}