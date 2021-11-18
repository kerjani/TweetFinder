package com.kernacs.tweetfinder.util

import io.ktor.client.statement.*


class MissingPageException(exceptionResponse: HttpResponse, exceptionResponseText: String) :
    Throwable() {

}
