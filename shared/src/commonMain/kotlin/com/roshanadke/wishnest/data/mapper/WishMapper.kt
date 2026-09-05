package com.roshanadke.wishnest.data.mapper

import com.roshanadke.wishnest.data.WishEntity
import com.roshanadke.wishnest.domain.Wish

fun WishEntity.toDomain(): Wish {
    return Wish(
        wishName = wishName,
        price = price,
        productLink = productLink,
        wishListType = wishListType,
        priority = priority,
        notes = notes
    )
}

fun Wish.toEntity(): WishEntity {
    return WishEntity(
        wishName = wishName,
        price = price,
        productLink = productLink,
        wishListType = wishListType,
        priority = priority,
        notes = notes
    )
}