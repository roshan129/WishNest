package com.roshanadke.wishnest.data

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface WishDao {
    @Insert
    suspend fun insert(item: WishEntity)

    @Delete
    suspend fun delete(item: WishEntity)

    @Query("SELECT count(*) FROM WishEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM WishEntity")
    fun getAllAsFlow(): Flow<List<WishEntity>>
}