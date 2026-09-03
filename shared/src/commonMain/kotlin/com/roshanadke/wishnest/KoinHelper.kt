package com.roshanadke.wishnest

import com.roshanadke.wishnest.di.sharedModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclarations: KoinAppDeclaration = { }): KoinApplication =
    startKoin {
        appDeclarations()
        modules(sharedModule)
    }
