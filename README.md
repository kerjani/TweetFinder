# Tweet Finder

The purpose of the application is to showcase the essentials of
a [modern Android app architecture](https://developer.android.com/modern-android-development).

## Features

The app displays search result of tweets from Twitter based on entered keywords and constantly shows
the new tweets in a constantly updating list.

* The app handles errors, empty states, loading, etc
* The source of the data is
  the [official Twitter streaming API](https://developer.twitter.com/en/docs/twitter-api/tweets/filtered-stream/quick-start)
* The items in the search result have a lifespan, meaning that after that time it will be removed
  from the list. This time frame of the lifespan can be also configured in the code (see below)
* If the app goes offline the result of the previous search keeps loaded and also the deletion of
  the expired items stops
* The app is distributed via Firebase App Distribution. You can join to the testers
  with [this invite link](https://appdistribution.firebase.dev/i/7103c94fe5fb2cc1)

## Technologies used:

* [Jetpack Compose](https://developer.android.com/jetpack/compose) Jetpack Compose is Android’s
  modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
    * **Unidirectional data flow** is a design where events flow up and state flows down. <br> For
      example, in a ViewModel events are passed up with method calls from the UI while state flows
      down using LiveData.
* [Material Design 3 (Material You)](https://m3.material.io/) A set of design system updates that
  enables more beautiful, personalized expression.
    * Dynamic color plays a key role in Material You, creating individualized and expressive
      experiences for the users and opening up new possibilities for the role of color in the app.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and
  manage UI-related data in a lifecycle conscious way.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to handle data in
  a lifecycle-aware fashion.
* [Ktor](https://ktor.io/) Lightweight networking library built from the ground up using Kotlin and
  Coroutines. You get to use a concise, multiplatform language, as well as the power of asynchronous
  programming with an intuitive imperative flow.
* [Hilt](https://dagger.dev/hilt/) for dependency injection.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) used to manage the
  local storage i.e. `writing to and reading from the database`. Coroutines help in managing
  background threads and reduces the need for callbacks.
* [Flow](https://developer.android.com/kotlin/flow) In coroutines, a flow is a type that can emit
  multiple values sequentially, as opposed to suspend functions that return only a single value.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which
  provides an abstraction layer over SQLite to allow for more robust database access while
  harnessing the full power of SQLite.
* [Android KTX](https://developer.android.com/kotlin/ktx) provides concise, idiomatic Kotlin to
  Jetpack, Android platform, and other APIs.
* [Firebase Analytics](https://firebase.google.com/products/analytics) Analytics of the screens
  usage across the users.
* [Firebase Crashlytics](https://firebase.google.com/products/crashlytics) Crash reports.

## Build instructions

The project uses values from `local.properties`:

* The Twitter API needs bearer token to work. It is stored in the  `local.properties` file in the
  root project folder.
* For more details about the authorization tokens used in the Twitter APIs check
  the [official documentation](https://developer.twitter.com/en/docs/authentication/oauth-2-0).
* THe lifespan of the items in the list can be also configured here (in milliseconds). Default value
  is 30000 (30 seconds).

The content of the `local.properties` file should look like this:

```
BEARER_TOKEN="TTTTTT"
LIFESPAN_IN_MILLISECONDS=30000
```

## LICENSE

MIT License

Copyright (c) 2021 János Kernács

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

```
