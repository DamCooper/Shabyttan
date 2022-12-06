package com.example.shabyttan.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtworkResponse(
    @SerializedName("data")
    val artworks: List<Artwork>,

) : Parcelable {
    constructor() : this(mutableListOf())

    override fun toString(): String {
        return "ArtworkResponse(artworks=$artworks)"
    }

}