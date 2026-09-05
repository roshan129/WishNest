package com.roshanadke.wishnest.domain

data class Wish(
    val wishName: String,
    val price: String,
    val productLink: String,
    val wishListType: String,
    val priority: String,
    val notes: String
)