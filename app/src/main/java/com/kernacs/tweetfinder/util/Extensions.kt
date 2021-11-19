package com.kernacs.tweetfinder.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.kernacs.tweetfinder.BuildConfig
import com.kernacs.tweetfinder.util.Constants.CONFIGURATION_TAG
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*


fun String.isoStringToDate(): Date {
    val temporalAccessor: TemporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(this)
    val instant: Instant = Instant.from(temporalAccessor)
    return Date.from(instant)
}

fun Date.toIsoString(): String {
    return DateTimeFormatter.ISO_INSTANT.format(this.toInstant())
}

fun Activity.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun Date.expirationTimeStamp(): Long {
    return time +
            try {
                val lifeSpan = BuildConfig.LIFESPAN_IN_MILLISECONDS.toLong()
                if (lifeSpan <= 0L) {
                    Log.e(
                        CONFIGURATION_TAG,
                        "Invalid lifespan configuration value, defaulting to ${Constants.DEFAULT_CONFIG_LIFESPAN} milliseconds."
                    )
                    Constants.DEFAULT_CONFIG_LIFESPAN
                } else {
                    lifeSpan
                }
            } catch (e: NumberFormatException) {
                Log.e(
                    CONFIGURATION_TAG,
                    "Invalid lifespan configuration value, defaulting to ${Constants.DEFAULT_CONFIG_LIFESPAN} milliseconds."
                )
                Constants.DEFAULT_CONFIG_LIFESPAN
            }
}