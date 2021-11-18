package com.kernacs.tweetfinder.data.local

import android.content.Context
//import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kernacs.tweetfinder.data.local.dao.TweetsDao
import com.kernacs.tweetfinder.data.local.entities.TweetEntity

@Database(
    entities = [TweetEntity::class],
    version = 1,
//    autoMigrations = [
//      AutoMigration(from = 1, to = 2)
//    ],
    exportSchema = true
)
abstract class TweetsDatabase : RoomDatabase() {
    abstract val tweetsDao: TweetsDao

    companion object {

        @Volatile
        private var instance: TweetsDatabase? = null

        fun getDatabase(context: Context): TweetsDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, TweetsDatabase::class.java, "TweetsDatabase")
                .fallbackToDestructiveMigration() // TODO delete when automigration will be implemented
                .build()

    }
}