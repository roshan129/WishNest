package com.roshanadke.wishnest.data

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class WishEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String
)