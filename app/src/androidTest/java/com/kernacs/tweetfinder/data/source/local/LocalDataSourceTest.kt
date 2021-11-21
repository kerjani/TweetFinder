package com.kernacs.tweetfinder.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.kernacs.tweetfinder.*
import com.kernacs.tweetfinder.data.local.TweetFinderLocalDataSource
import com.kernacs.tweetfinder.data.local.TweetsDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class TweetLocalDataSourceTest {

    private lateinit var database: TweetsDatabase

    private lateinit var systemUnderTest: TweetFinderLocalDataSource

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TweetsDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        systemUnderTest = TweetFinderLocalDataSource(database.tweetsDao)
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun saveTweetCheckNoIfInserted() = mainCoroutineRule.runBlockingTest {
        systemUnderTest.saveTweet(fakeEntity)

        val returnedTweets = async {
            systemUnderTest.getTweets().take(1).toList()
        }.await().flatten()

        assertThat(returnedTweets.size, `is`(1))
        assert(returnedTweets.contains(fakeEntity))

    }

    @Test
    fun saveTweetDeleteAllCheckNoItemsLeft() = mainCoroutineRule.runBlockingTest {
        systemUnderTest.saveTweet(fakeEntity)

        var result = async {
            systemUnderTest.getTweets().take(1).toList()
        }.await().flatten()
        assertThat(result.size, `is`(1))
        assertThat(result.contains(fakeEntity), `is`(true))

        systemUnderTest.deleteAllTweets()

        result = async {
            systemUnderTest.getTweets().take(1).toList()
        }.await().flatten()
        assertThat(result.size, `is`(0))
    }

    @Test
    fun saveExpiredDeleteAllExpiredItIsDeleted() = mainCoroutineRule.runBlockingTest {
        systemUnderTest.saveTweet(expiredEntity)

        var result = async {
            systemUnderTest.getTweets().take(1).toList()
        }.await().flatten()
        assertThat(result.size, `is`(1))
        assertThat(result.contains(expiredEntity), `is`(true))


        systemUnderTest.deleteExpiredData(expirationReferenceTimeStamp)

        result = async {
            systemUnderTest.getTweets().take(1).toList()
        }.await().flatten()
        assertThat(result.size, `is`(0))
    }

    @Test
    fun saveNotYetExpiredDeleteAllExpiredItRemains() = mainCoroutineRule.runBlockingTest {
        systemUnderTest.saveTweet(notYetExpiredEntity)

        var result = async {
            systemUnderTest.getTweets().take(1).toList()
        }.await().flatten()
        assertThat(result.size, `is`(1))
        assertThat(result.contains(notYetExpiredEntity), `is`(true))

        systemUnderTest.deleteExpiredData(expirationReferenceTimeStamp)

        result = async {
            systemUnderTest.getTweets().take(1).toList()
        }.await().flatten()
        assertThat(result.size, `is`(1))
    }

}
