[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/e4rOHRfR)

# Android App 3 - Campus Maps
Name: Saaswath Kumar

Computing ID: anr6nr

# IMPORTANT PLEASE READ:
The code will not work without a maps api key. I emailed the TAs and Professor Morrison the key, so take that key or a key of your own and insert it in local.properties like this:
 ```kotlin
MAPS_API_KEY=INSERTKEYHERE
```

## Code Structure

model:
* Contains all the Room, Dao, and data class stuff
* Files: 
Placemark.kt, PlmApiService.kt, PlmDao.kt, PlmDatabase.kt, PlmRepo.kt, PlmResponseDetails.kt, PlmResponseDetails.kt, PlmTag.kt

view:
* MainActivity launches GroundsMapScreen, which contains the view stuff.
* Files: GroundsMapScreen.kt


viewmodel
* Contains PlmViewModel 

## Citations

PlmApiService
 * REFERENCES
 * Title:  Retrofit Documentation
 * Author: Square Inc
 * URL:https://square.github.io/retrofit/
 * Software License: Apache 2.0 License
 * Usage: I used this as a reference for integrating retrofit into my project

PlmDao
 * REFERENCES
 * Title: Accessing data using Room DAOs
 * Author: Android Developers
 * URL: https://developer.android.com/training/data-storage/room/accessing-data?utm_source=android-studio-app&utm_medium=app
 * Software License: Apache 2 License
 * Usage: I used this as a reference for configuring my Dao setup.

PlmDatabase
 * REFERENCES
 * Title: Persist data with Room
 * Author: Android Developers
 * URL:https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room?authuser=2#0
 * Date: May 17, 2024
 * Software License: Apache 2 License
 * Usage: I used this as reference for my Room implementation.

GroundsMapScreen
 * REFERENCES
 * Title:  Maps Compose Library
 * Author: Google Maps Platform
 * URL:https://developers.google.com/maps/documentation/android-sdk/maps-compose
 * Software License: Apache 2 License
 * Usage: I used this as reference to implement the Google map implementation in my code