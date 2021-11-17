package com.kernacs.tweetfinder.network

import com.kernacs.tweetfinder.data.remote.dto.AddRulesRequestDto
import com.kernacs.tweetfinder.data.remote.dto.DeleteRulesRequestDto
import com.kernacs.tweetfinder.data.remote.dto.DeleteRulesResponseDto
import com.kernacs.tweetfinder.data.remote.dto.GetRulesResponseDto
import com.kernacs.tweetfinder.util.Constants.V2_SEARCH_RULES_ENDPOINT
import com.kernacs.tweetfinder.util.Constants.V2_SEARCH_STREAM_ENDPOINT
import com.kernacs.tweetfinder.util.Constants.withBaseUrl
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import javax.inject.Inject


class TwitterApi @Inject constructor(private val httpClient: HttpClient) {

    suspend fun getRules() =
        httpClient.get<GetRulesResponseDto>(V2_SEARCH_RULES_ENDPOINT.withBaseUrl())

    suspend fun addRules(rule: String) =
        httpClient.post<GetRulesResponseDto>(V2_SEARCH_RULES_ENDPOINT.withBaseUrl()) {
            body = AddRulesRequestDto(listOf(AddRulesRequestDto.Rule(value = rule)))
        }

    suspend fun deleteRules(vararg ids: String) =
        httpClient.post<DeleteRulesResponseDto>(V2_SEARCH_RULES_ENDPOINT.withBaseUrl()) {
            body = DeleteRulesRequestDto(DeleteRulesRequestDto.Delete(ids.toList()))
        }

    suspend fun search() = httpClient.get<HttpStatement>(V2_SEARCH_STREAM_ENDPOINT.withBaseUrl()) {
        parameter("tweet.fields", "id,author_id,public_metrics,created_at,source")
        parameter("expansions", "author_id")
        parameter("user.fields", "id,name,profile_image_url,username")
    }

}