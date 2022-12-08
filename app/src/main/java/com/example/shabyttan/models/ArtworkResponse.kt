package com.example.shabyttan.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtworkResponse(
    @SerializedName("data")
    val artData: ArtData,

) : Parcelable {
    override fun toString(): String {
        return "ArtworkResponse(artworks=$artData)"
    }

}