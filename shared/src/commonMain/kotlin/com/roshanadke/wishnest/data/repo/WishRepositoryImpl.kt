package com.roshanadke.wishnest.data.repo

import com.roshanadke.wishnest.data.WishDao
import com.roshanadke.wishnest.data.mapper.toDomain
import com.roshanadke.wishnest.data.mapper.toEntity
import com.roshanadke.wishnest.domain.Wish
import com.roshanadke.wishnest.domain.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WishRepositoryImpl(
    private val wishDao: WishDao
): WishRepository {
    override fun getAllWishes(): Flow<List<Wish>> {
        return wishDao.getAllAsFlow().map { wishEntities ->
            wishEntities.map { it.toDomain() }
        }
    }

    override suspend fun addWish(wish: Wish) {
        wishDao.insert(wish.toEntity())
    }

    override suspend fun deleteWish(wish: Wish) {
        wishDao.delete(wish.toEntity())
    }
}