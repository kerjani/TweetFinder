package com.kernacs.tweetfinder.data.repository

import android.util.Log
import com.kernacs.tweetfinder.TweetSearchViewModel
import com.kernacs.tweetfinder.data.local.LocalDataSource
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import com.kernacs.tweetfinder.data.mapping.toEntity
import com.kernacs.tweetfinder.data.remote.RemoteDataSource
import io.ktor.utils.io.*
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweetFinderRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : Repository {

    private var runningSearch: ByteReadChannel? = null

    private var lastExpiredDataDeleted = 0L

    override fun cancelSearch() {
        runningSearch?.apply {
            cancel()
        }
    }

    override suspend fun searchTweets(term: String) = flow {
        Log.d(TAG, "Data loading has been started")
        try {
            emit(TweetSearchViewModel.ViewState.Loading)
            cancelSearch()
            remoteDataSource.search(term, { byteChannel ->
                runningSearch = byteChannel
                emit(TweetSearchViewModel.ViewState.WaitingForStream)
                deleteTweets()
                Log.d(TweetSearchViewModel.TAG, "Waiting for the search result to be returned")
            }) { newItem ->
                if (lastExpiredDataDeleted + DELETE_EXPIRED_DATA_MINIMAL_INTERVAL <= Date().time) {
                    Log.d(TAG, "Starting deletion of expired tweets")
                    val referenceDate = Date().time
                    deleteExpiredTweets(referenceDate)
                    lastExpiredDataDeleted = referenceDate
                }
                saveTweet(newItem.toEntity())

                emit(TweetSearchViewModel.ViewState.SearchResult) // refresh list
            }
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            emit(TweetSearchViewModel.ViewState.Error(e))
        }
    }

    override fun getTweets() = localDataSource.getTweets()

    override suspend fun deleteExpiredTweets(timeStamp: Long) =
        localDataSource.deleteExpiredData(timeStamp)

    override suspend fun insert(tweet: TweetEntity) =
        localDataSource.saveTweet(tweet)

    override suspend fun deleteTweets() = localDataSource.deleteAllTweets()

    private suspend fun saveTweet(tweet: TweetEntity) = localDataSource.saveTweet(tweet)

    companion object {
        const val TAG = "TweetFinderRepository"
        const val DELETE_EXPIRED_DATA_MINIMAL_INTERVAL = 5000 //milliseconds
    }
}