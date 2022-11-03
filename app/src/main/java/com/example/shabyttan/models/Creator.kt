package com.example.shabyttan.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Creator(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("biography")
    val biography: String?,

    @SerializedName("birth_year")
    val birth_year: String?,

    @SerializedName("death_year")
    val death_year: String?,
    val artworks: List<Artwork>,
    ) : Parcelable



