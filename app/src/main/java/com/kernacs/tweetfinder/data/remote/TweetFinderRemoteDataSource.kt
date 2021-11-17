package com.kernacs.tweetfinder.data.remote

import android.util.Log
import com.kernacs.tweetfinder.data.remote.dto.TweetDto
import com.kernacs.tweetfinder.network.TwitterApi
import io.ktor.client.call.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TweetFinderRemoteDataSource @Inject constructor(
    private val api: TwitterApi,
) : RemoteDataSource {

    private suspend fun setupRules(term: String) {
        api.getRules().data?.let { rules ->
            Log.d(TAG, rules.toString())
            if (rules.isNotEmpty()) {
                val ids = rules.map { it.id }.toTypedArray()
                val deleteResult = api.deleteRules(*ids)
                Log.d(TAG, deleteResult.toString())
            }
        }
        val addResult = api.addRules(term)
        Log.d(TAG, addResult.toString())
    }

    override suspend fun search(term: String, onNewTweet: (TweetDto) -> Unit) {
        setupRules(term)

        api.search().execute { httpResponse ->
            val channel: ByteReadChannel = httpResponse.receive()

            while (!channel.isClosedForRead) {
                channel.readUTF8Line()?.let { line ->
                    Log.d(TAG, line)
                    if (line.isEmpty()) return@let
                    try {
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        }.decodeFromString(TweetDto.serializer(), line).let { tweet ->
                            onNewTweet(tweet)
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, e.message.toString())
                    }

                }
            }
        }
    }

    companion object {
        const val TAG = "RemoteDataSource"
    }
}