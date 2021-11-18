package com.kernacs.tweetfinder.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
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