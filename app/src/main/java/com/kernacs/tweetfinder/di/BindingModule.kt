package com.kernacs.tweetfinder.di

import com.kernacs.tweetfinder.data.local.LocalDataSource
import com.kernacs.tweetfinder.data.local.TweetFinderLocalDataSource
import com.kernacs.tweetfinder.data.remote.RemoteDataSource
import com.kernacs.tweetfinder.data.remote.TweetFinderRemoteDataSource
import com.kernacs.tweetfinder.data.repository.Repository
import com.kernacs.tweetfinder.data.repository.TweetFinderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindRemoteDataSource(impl: TweetFinderRemoteDataSource): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(repositoryImpl: TweetFinderLocalDataSource): LocalDataSource

    @Binds
    abstract fun bindRepository(repositoryImpl: TweetFinderRepository): Repository
}