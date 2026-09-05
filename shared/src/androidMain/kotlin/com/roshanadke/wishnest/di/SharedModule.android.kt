package com.roshanadke.wishnest.di

import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.roshanadke.wishnest.data.WishDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

const val DATABASE_NAME = "wish_database.db"

actual val platformModule: Module
    get() = module {
        single<RoomDatabase.Builder<WishDatabase>> {
            val context = androidContext()
            val dbFile = context.getDatabasePath("wish_database.db")
            Room.databaseBuilder(
                context,
                WishDatabase::class.java,
                "wish_database"
            )
        }
    }