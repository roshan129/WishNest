package com.roshanadke.wishnest.navigation
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
object HomeScreen: NavKey

@Serializable
object AddWishScreen: NavKey

val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeScreen::class, HomeScreen.serializer())
            subclass(AddWishScreen::class, AddWishScreen.serializer())
        }
    }
}