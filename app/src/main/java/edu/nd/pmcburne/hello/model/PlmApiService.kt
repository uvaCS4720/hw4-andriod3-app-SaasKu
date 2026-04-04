package edu.nd.pmcburne.hello.model

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
/***************************************************************************************
 * REFERENCES
 * Title:  Retrofit Documentation
 * Author: Square Inc
 * URL:https://square.github.io/retrofit/
 * Software License: Apache 2.0 License
 * Usage: I used this as a reference for integrating retrofit into my project
 ***************************************************************************************/
private const val baseUrl = "https://www.cs.virginia.edu/"


interface PlmApiService {
    @GET("~wxt4gm/placemarks.json")
    suspend fun getPlacemarks(): List<PlmResponseDetails>
}

object RetrofitInst{
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: PlmApiService = retrofit.create(PlmApiService::class.java)

}