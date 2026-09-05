package com.roshanadke.wishnest.di

import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.roshanadke.wishnest.data.WishDatabase
import com.roshanadke.wishnest.data.WishDatabaseConstructor
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual val platformModule: Module
    get() = module {
        single<RoomDatabase.Builder<WishDatabase>> {
            val dbFile = File(System.getProperty("user.home"), "wish_database.db")

            Room.databaseBuilder(
                name = dbFile.absolutePath,
                factory = { WishDatabaseConstructor.initialize() }
            )
        }
    }