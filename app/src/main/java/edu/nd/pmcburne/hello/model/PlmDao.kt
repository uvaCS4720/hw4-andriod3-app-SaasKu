package edu.nd.pmcburne.hello.model

import androidx.room.*
/***************************************************************************************
 * REFERENCES
 * Title: Accessing data using Room DAOs
 * Author: Android Developers
 * URL: https://developer.android.com/training/data-storage/room/accessing-data?utm_source=android-studio-app&utm_medium=app
 * Software License: Apache 2 License
 * Usage: I used this as a reference for configuring my Dao setup.
 ***************************************************************************************/
@Dao
interface PlmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlms(plms: List<Placemark>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTags(tags: List<PlmTag>)

    @Query("DELETE FROM plm_tag_table")
    suspend fun deleteAllTags()

    @Query("""
        SELECT DISTINCT tag
        FROM plm_tag_table
        ORDER BY tag ASC
    """)
    suspend fun getAllTags(): List<String>

    @Query("""
        SELECT p.*
        FROM placemark_table p
        INNER JOIN plm_tag_table t
        ON p.id = t.placemarkId
        WHERE t.tag = :selectedTag
        ORDER BY p.name ASC
    """)
    suspend fun getPlmsForTag(selectedTag: String): List<Placemark>

}