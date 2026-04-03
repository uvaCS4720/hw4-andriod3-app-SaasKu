package edu.nd.pmcburne.hello.model

import androidx.room.*

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