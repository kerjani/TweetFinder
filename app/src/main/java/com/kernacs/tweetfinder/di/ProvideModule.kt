package com.kernacs.tweetfinder.di

import com.kernacs.tweetfinder.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class ProvideModule {

    @Provides
    @Named(Constants.HILT_INJECTION_TEST)
    fun provideTestString() = "Hilt dependency injection works"

}