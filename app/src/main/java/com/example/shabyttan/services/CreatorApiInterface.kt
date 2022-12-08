package com.example.shabyttan.services

import com.example.shabyttan.models.ArtworkResponse
import com.example.shabyttan.models.CreatorResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CreatorApiInterface {

    @GET("/api/creators/")
    fun getCreatorList(@Query("name") name: String): Call<CreatorResponse>
    @GET("/api/artworks/{id}")
    fun getArtworksList(@Path("id") id: Int): Call<ArtworkResponse>
}