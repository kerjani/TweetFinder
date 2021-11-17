package com.kernacs.tweetfinder.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeleteRulesRequestDto(
    val delete: Delete
) {
    @Serializable
    data class Delete(
        val ids: List<String>
    )
}