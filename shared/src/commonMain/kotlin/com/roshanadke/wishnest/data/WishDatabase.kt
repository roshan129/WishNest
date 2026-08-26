package com.roshanadke.wishnest.data

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor

@Database(entities = [WishEntity::class], version = 1)
@ConstructedBy(WishDatabaseConstructor::class)
abstract class WishDatabase : RoomDatabase() {
    abstract fun getDao(): WishDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object WishDatabaseConstructor : RoomDatabaseConstructor<WishDatabase> {
    override fun initialize(): WishDatabase
}