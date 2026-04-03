package edu.nd.pmcburne.hello.model

import androidx.room.*

import android.content.Context

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