package com.roshanadke.wishnest.di

import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.roshanadke.wishnest.data.WishDatabase
import com.roshanadke.wishnest.data.WishDatabaseConstructor
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual val platformModule: Module
    get() = module {

        single<RoomDatabase.Builder<WishDatabase>> {
            val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )

            val dbFilePath = documentDirectory?.path + "/wishnest.db"

            Room.databaseBuilder(
                name = dbFilePath,
                factory = { WishDatabaseConstructor.initialize() }
            )
        }
    }