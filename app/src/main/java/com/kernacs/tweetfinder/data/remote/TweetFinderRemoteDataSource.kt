package com.kernacs.tweetfinder.data.remote

import android.util.Log
import com.google.gson.Gson
import com.kernacs.tweetfinder.data.remote.dto.TweetSearchDto
import com.kernacs.tweetfinder.network.TwitterApi
import com.kernacs.tweetfinder.util.Result
import okhttp3.ResponseBody
import okio.Buffer
import java.nio.charset.Charset
import javax.inject.Inject

class TweetFinderRemoteDataSource @Inject constructor(
    private val api: TwitterApi,
) : RemoteDataSource {

    override suspend fun search(term: String): Result<List<TweetSearchDto.Tweet>> =
        try {
            val result = api.search(term)
            if (result.isSuccessful) {
                result.body()?.let {
                    Result.Success(it.statuses)
                }
                    ?: Result.Error(Exception("Download of search result for query $term has been failed"))
            } else {
                Result.Error(Exception(result.errorBody().toString()))
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }


    override suspend fun streamTweets(terms: String, callback: (String) -> Unit) {
        api.searchTweetsByTerm(terms)
            .enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }

                override fun onResponse(
                    call: retrofit2.Call<ResponseBody>, response: retrofit2.Response<ResponseBody>
                ) {
                    Log.d(TAG, response.raw().toString()) // never ever gets here
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body()?.string() ?: "no body")

                        val source = response.body()?.source()
                        val buffer = Buffer()
                        var result = ""
                        while (!source!!.exhausted()) {
                            source.read(buffer, 8192)
                            val data = buffer.readString(Charset.defaultCharset())
                            Log.w("stream data", data)
                            result += "\n$data"
                            try {
                                val tweetResponseModel: TweetSearchDto.Tweet =
                                    Gson().fromJson(data, TweetSearchDto.Tweet::class.java)
                                Log.d(TAG, "data parsed successfully: $tweetResponseModel")
                                // TODO emit newly created parsed object
                            } catch (e: Exception) {
                                Log.e(TAG, data)
                            }
                        }

                        callback(result)
                    } else {
                        Log.e(TAG, response.errorBody()?.string() ?: "no errorbody")
                        callback(response.errorBody().toString())
                    }
                }
            })

    }

    companion object {
        const val TAG = "RemoteDataSource"
    }
}