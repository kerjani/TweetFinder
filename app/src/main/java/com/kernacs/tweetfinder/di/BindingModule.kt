package com.kernacs.tweetfinder.di

import com.kernacs.tweetfinder.data.remote.RemoteDataSource
import com.kernacs.tweetfinder.data.remote.TweetFinderRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindRemoteDataSource(impl: TweetFinderRemoteDataSource): RemoteDataSource

}