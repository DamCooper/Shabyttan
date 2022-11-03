package com.example.shabyttan.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artwork(
    @SerializedName("accession_number")
    val accession_number: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
) : Parcelable {
    constructor() : this(
        "", "", "",
    )
}