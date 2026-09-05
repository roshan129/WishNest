package com.roshanadke.wishnest.di

import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.roshanadke.wishnest.data.WishDatabase
import com.roshanadke.wishnest.data.repo.WishRepositoryImpl
import com.roshanadke.wishnest.domain.WishRepository
import com.roshanadke.wishnest.viewmodel.WishViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule = module {
    single<WishDatabase> {
        val builder: RoomDatabase.Builder<WishDatabase> = get()

        builder.setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single {
        get<WishDatabase>().getDao()
    }

    single<WishRepository> {
        WishRepositoryImpl(get())
    }

    factory {
        WishViewModel(get())
    }
}

expect val platformModule: Module
