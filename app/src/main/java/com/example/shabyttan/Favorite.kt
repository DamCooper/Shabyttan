package com.example.shabyttan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "profile_id")
    val profile_id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "creator")
    val creator: String

) {
    override fun toString(): String {
        return "Favorite(id=$id, profile_id=$profile_id, title='$title', creator='$creator')"
    }
}