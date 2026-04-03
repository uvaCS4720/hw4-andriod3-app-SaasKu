package edu.nd.pmcburne.hello.model

import androidx.room.*

@Entity(
    tableName = "placemark_table"
)
data class Placemark(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
)
