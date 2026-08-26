package com.roshanadke.wishnest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform