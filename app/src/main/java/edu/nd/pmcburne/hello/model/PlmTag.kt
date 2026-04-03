package edu.nd.pmcburne.hello.model
import androidx.room.*

@Entity(
    tableName = "plm_tag_table",
    primaryKeys = ["placemarkId", "tag"]
)
data class PlmTag(
    val placemarkId: Int,
    val tag: String
)
