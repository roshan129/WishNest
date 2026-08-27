package com.roshanadke.wishnest

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.roshanadke.wishnest.navigation.AddWishScreen
import com.roshanadke.wishnest.navigation.HomeScreen
import com.roshanadke.wishnest.navigation.config
import com.roshanadke.wishnest.ui.AddWishScreen
import com.roshanadke.wishnest.ui.HomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        WishListApp()
    }
}

@Composable
fun WishListApp(
    modifier: Modifier = Modifier,
) {

    val backStack = rememberNavBackStack(config, HomeScreen)

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        entryProvider = entryProvider {
            entry<HomeScreen> {
                HomeScreen(
                    onAddButtonClicked = {
                        backStack.add(AddWishScreen)
                    }
                )
            }
            entry<AddWishScreen> {
                AddWishScreen(
                    onBackPressed = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}
