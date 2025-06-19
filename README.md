

Compose playground for learning purpose

* UI completely in [Jetpack Compose](https://developer.android.com/jetpack/compose)
* Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
* Uses [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
* Uses many of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/), including: Room, DataStore, Navigation, ViewModel
* Uses [Hilt](https://dagger.dev/hilt/) for dependency injection
* Uses [Java 8+ API desugaring support](https://developer.android.com/studio/write/java8-support#library-desugaring) for date and time usage

<img src="art/ps-1.png" width="260">  <img src="art/ps-2.png" width="260">  <img src="art/ps-3.png" width="260">

## Large screen

 <img src="art/landscape.gif" width="400"> <img src="art/foldable.gif" width="260">

## Prerequisites

* Android Studio Iguana | 2023.2.1
* Min SDK 21
* Target SDK 34
* AGP 8.3.2
* Java 17
* Kotlin 2.0.20

## How to build

* Generate debug apk `./gradlew assembleDebug`
* Run unit test `./gradlew testDebug`
* Install on connected device `./gradlew installDebug`

