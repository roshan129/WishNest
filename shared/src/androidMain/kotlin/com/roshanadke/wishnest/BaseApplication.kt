package com.roshanadke.wishnest

import android.app.Application
import com.roshanadke.wishnest.di.sharedModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModules(sharedModule)
    }

}