package com.example.shabyttan.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtworkResponse(
    @SerializedName("artworks")
    val artworks: List<Artwork>,

) : Parcelable {
    constructor() : this(mutableListOf())
}