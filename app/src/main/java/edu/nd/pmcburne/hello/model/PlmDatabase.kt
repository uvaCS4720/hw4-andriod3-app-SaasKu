package edu.nd.pmcburne.hello.model

import androidx.room.*

import android.content.Context

/***************************************************************************************
 * REFERENCES
 * Title: Persist data with Room
 * Author: Android Developers
 * URL:https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room?authuser=2#0
 * Date: May 17, 2024
 * Software License: Apache 2 License
 * Usage: I used this as reference for my Room implementation.
 ***************************************************************************************/

@Database(
    entities = [Placemark::class, PlmTag::class],
    version = 1,
    exportSchema = false
)
abstract class PlmDatabase: RoomDatabase() {
    abstract fun plmDao(): PlmDao

    companion object {
        @Volatile
        private var inst: PlmDatabase?= null

        fun getDatabase(context: Context): PlmDatabase{
            return inst ?: synchronized(lock=this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlmDatabase::class.java,
                    "plm_database"
                ).build()
                inst = instance
                instance
            }
        }
    }

}