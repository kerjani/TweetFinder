package com.kernacs.tweetfinder.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteRulesResponseDto(
    val meta: Meta
) {
    @Serializable
    data class Meta(
        val sent: String,
        val summary: Summary
    ) {
        @Serializable
        data class Summary(
            val deleted: Int,
            @SerialName("not_deleted")
            val notDeleted: Int
        )
    }
}