package com.kernacs.tweetfinder.di

import com.kernacs.tweetfinder.BuildConfig
import com.kernacs.tweetfinder.network.TwitterApi
import com.kernacs.tweetfinder.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class ProvideModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val consumer = OkHttpOAuthConsumer(BuildConfig.API_KEY, BuildConfig.API_KEY_SECRET)
        consumer.setTokenWithSecret(BuildConfig.ACCESS_TOKEN, BuildConfig.ACCESS_TOKEN_SECRET)

        val builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(SigningInterceptor(consumer))

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_API_V1_URL)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): TwitterApi = retrofit.create(
        TwitterApi::class.java
    )

    companion object {
        private const val TIME_OUT_SECONDS = 60L
    }

}