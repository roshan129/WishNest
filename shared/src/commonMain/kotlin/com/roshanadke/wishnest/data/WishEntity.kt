package com.roshanadke.wishnest.data

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class WishEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val wishName: String,
    val price: String,
    val productLink: String,
    val wishListType: String,
    val priority: String,
    val notes: String
)