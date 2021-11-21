package com.kernacs.tweetfinder.data.local

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.kernacs.tweetfinder.data.local.dao.TweetsDao
import com.kernacs.tweetfinder.data.local.entities.TweetEntity

@Database(
    entities = [TweetEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = TweetsDatabase.AutoMigration1To2::class)
    ],
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
                .build()

    }

    @DeleteColumn(
        tableName = "tweets",
        columnName = "source"
    )
    class AutoMigration1To2 : AutoMigrationSpec {}
}