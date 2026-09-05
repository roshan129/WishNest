package com.roshanadke.wishnest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshanadke.wishnest.domain.Wish
import com.roshanadke.wishnest.domain.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository
): ViewModel() {

    val allWishes: Flow<List<Wish>> = wishRepository.getAllWishes()

    fun addWish(wish: Wish) {
        viewModelScope.launch {
            wishRepository.addWish(wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch {
            wishRepository.deleteWish(wish)
        }
    }

}