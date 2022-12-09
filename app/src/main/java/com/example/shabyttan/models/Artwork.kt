package com.example.shabyttan.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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

    override fun toString(): String {
        return "Artwork(accession_number='$accession_number', id='$id', title='$title')"
    }

}


@Parcelize
data class ArtData(
    var accession_number: @RawValue String,
    val artists_tags: @RawValue List<String>,
    val catalogue_raisonne: @RawValue Any,
    val citations: @RawValue List<Citation>,
    val collection: @RawValue String,
    val copyright: @RawValue String,
    val creation_date: @RawValue String,
    val creation_date_earliest: @RawValue Int,
    val creation_date_latest: @RawValue Int,
    val creators: @RawValue List<Creators>,
    val creditline: @RawValue String,
    val culture: @RawValue List<String>,
    val current_location: @RawValue Any,
    val department: @RawValue String,
    val digital_description: @RawValue String,
    val dimensions: @RawValue Dimensions,
    val edition_of_the_work: @RawValue Any,
    val exhibitions: @RawValue Exhibitions,
    val external_resources: @RawValue ExternalResources,
    val find_spot: @RawValue Any,
    val former_accession_numbers: @RawValue List<Any>,
    val fun_fact: @RawValue String,
    val id: @RawValue Int,
    val images: @RawValue Images,
    val inscriptions: @RawValue List<Any>,
    val measurements: @RawValue String,
    val provenance: @RawValue List<Provenance>,
    val related_works: @RawValue List<RelatedWork>,
    val series: @RawValue Any,
    val series_in_original_language: @RawValue Any,
    val share_license_status: @RawValue String,
    val state_of_the_work: @RawValue Any,
    val support_materials: @RawValue List<Any>,
    val technique: @RawValue String,
    val title: @RawValue String,
    val title_in_original_language: @RawValue Any,
    val tombstone: @RawValue String,
    val type: @RawValue String,
    val updated_at: @RawValue String,
    val url: @RawValue String,
    val wall_description: @RawValue String
) : Parcelable {

    override fun toString(): String {
        return "Artwork(accession_number='$accession_number', id='$id', title='$title')"
    }

}

data class Citation(
    val citation: String,
    val page_number: String,
    val url: Any
)


data class Creators(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("extent") var extent: String? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("biography") var biography: String? = null,
    @SerializedName("name_in_original_language") var nameInOriginalLanguage: String? = null,
    @SerializedName("birth_year") var birthYear: String? = null,
    @SerializedName("death_year") var deathYear: String? = null

)

data class Dimensions(
    val sheet: Sheet
)

data class Exhibitions(
    val current: List<Current>,
    val legacy: List<String>
)

data class ExternalResources(
    val internet_archive: List<Any>,
    val wikidata: List<Any>
)

data class Provenance(
    val citations: List<Any>,
    val date: String,
    val description: String,
    val footnotes: List<Any>
)

data class RelatedWork(
    val description: String,
    val id: Int,
    val relationship: Any
)

data class Sheet(
    val height: Double,
    val width: Double
)

data class Current(
    val description: String,
    val id: Int,
    val opening_date: String,
    val title: String
)



data class Images(
    val full: Full,
    val print: Print,
    val web: Web
)

data class Full(
    val filename: String,
    val filesize: String,
    val height: String,
    val url: String,
    val width: String
)

data class Print(
    val filename: String,
    val filesize: String,
    val height: String,
    val url: String,
    val width: String
)

data class Web(
    val filename: String,
    val filesize: String,
    val height: String,
    val url: String,
    val width: String
)