package com.roshanadke.wishnest

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.roshanadke.wishnest.di.initKoin

fun main() = application {



    Window(
        onCloseRequest = ::exitApplication,
        title = "WishNest",
    ) {
        App()
    }
}