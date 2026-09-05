package com.roshanadke.wishnest

import android.app.Application
import com.roshanadke.wishnest.di.initKoin
import org.koin.android.ext.koin.androidContext

class WishApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@WishApp)
        }
    }

}