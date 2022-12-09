package com.example.shabyttan.services

import com.example.shabyttan.models.ArtworkResponse
import com.example.shabyttan.models.ArtworkResponse1
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

    @GET("/api/artworks/?has_image=1")
    fun getArtworks(): Call<ArtworkResponse1>

    @GET("/api/artworks/?has_image=1")
    fun getArtworksByKey(@Query("q") key: String): Call<ArtworkResponse1>

}