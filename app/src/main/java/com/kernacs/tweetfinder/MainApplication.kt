package com.kernacs.tweetfinder

import android.app.Application
import android.util.Log
import com.kernacs.tweetfinder.util.Constants.CONFIGURATION_TAG
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        checkBuildParameters()
    }

    private fun checkBuildParameters() {
        if (BuildConfig.BEARER_TOKEN.isEmpty()) {
            Log.e(
                CONFIGURATION_TAG,
                "Local properties config of BEARER_TOKEN is empty or missing. See the README for details! "
            )
        }
        try {
            if (BuildConfig.LIFESPAN_IN_MILLISECONDS.toLong() <= 0L) {
                Log.e(
                    CONFIGURATION_TAG,
                    "Local properties config LIFESPAN_IN_MILLISECONDS is wrong or missing. See the README for details! "
                )
            }
        } catch (e: NumberFormatException) {
            Log.e(
                CONFIGURATION_TAG,
                "Local properties config LIFESPAN_IN_MILLISECONDS has wrong format. It should be a valid positive integer. See the README for details! "
            )
        }
    }

}