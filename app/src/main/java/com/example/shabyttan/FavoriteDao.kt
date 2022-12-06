package com.example.shabyttan

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites ORDER BY id")
    fun getFavorites(): Flow<List<Favorite>>

    @Update
    fun updateFavorite(favorite: Favorite)

    @Delete
    fun deleteFavorite(favorite: Favorite)


}