package com.roshanadke.wishnest.domain

import kotlinx.coroutines.flow.Flow

interface WishRepository {
    fun getAllWishes(): Flow<List<Wish>>
    suspend fun addWish(wish: Wish)
    suspend fun deleteWish(wish: Wish)
}