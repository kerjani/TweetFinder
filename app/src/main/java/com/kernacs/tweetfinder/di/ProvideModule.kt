package com.kernacs.tweetfinder.di

import android.util.Log
import com.kernacs.tweetfinder.BuildConfig
import com.kernacs.tweetfinder.network.TwitterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*


@Module
@InstallIn(SingletonComponent::class)
class ProvideModule {

    @Provides
    fun provideHttpClient() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "Bearer ${BuildConfig.BEARER_TOKEN}")
        }

        expectSuccess = false
        HttpResponseValidator {
            handleResponseException { exception ->
                throw exception
            }
        }
    }


    @Provides
    fun scooterApi(client: HttpClient): TwitterApi = TwitterApi(client)

    companion object {
        private const val TIME_OUT = 60_000
    }

}