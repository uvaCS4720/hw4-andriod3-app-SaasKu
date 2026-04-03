package edu.nd.pmcburne.hello.model

data class VisualCenter(
    val latitude: Double,
    val longitude: Double
)
data class PlmResponseDetails(
    val id: Int,
    val name: String,
    val tag_list: List<String>,
    val description: String,
    val visual_center: VisualCenter
){
    fun convertToPlm(): Placemark{
        val center = visual_center
        return Placemark(
            id = id,
            name = name,
            description = description,
            latitude = center.latitude,
            longitude = center.longitude
        )
    }

    fun convertToPlmTags(): List<PlmTag>{
        return tag_list.map {
            tag ->
            PlmTag(
                placemarkId = id,
                tag = tag
            )
        }
    }
}