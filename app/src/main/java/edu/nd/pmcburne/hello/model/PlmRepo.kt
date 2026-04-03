package edu.nd.pmcburne.hello.model

class PlmRepo(private val dao: PlmDao) {
    suspend fun syncPlacemarks(){
        val response = RetrofitInst.api.getPlacemarks()

        val placemarks = response.map{it.convertToPlm()}
        val tags = response.flatMap { it.convertToPlmTags() }

        dao.insertPlms(placemarks)

        dao.deleteAllTags()
        dao.insertTags(tags)
    }

    suspend fun getAllTags(): List<String>{
        return dao.getAllTags()
    }

    suspend fun getPlmsForTag(tag: String):List<Placemark>{
        return dao.getPlmsForTag(tag)
    }
}