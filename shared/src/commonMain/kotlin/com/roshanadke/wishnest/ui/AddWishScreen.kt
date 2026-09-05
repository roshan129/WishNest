package com.roshanadke.wishnest.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.roshanadke.wishnest.domain.Wish
import com.roshanadke.wishnest.viewmodel.WishViewModel
import org.koin.mp.KoinPlatform.getKoin

enum class WishPriority { Low, Medium, High }

val wishListTypes = listOf(
    "Tech Wishlist",
    "Birthday Wishlist",
    "Travel Wishlist",
    "Home & Living",
    "Fashion",
    "Books",
    "Other"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWishScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}
) {
    val viewModel: WishViewModel = remember { getKoin().get() }

    // URL-first mode state
    var isManualMode by remember { mutableStateOf(false) }
    var productUrl by remember { mutableStateOf("") }

    // Form fields
    var wishName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var productLink by remember { mutableStateOf("") }
    var selectedWishList by remember { mutableStateOf(wishListTypes.first()) }
    var dropdownExpanded by remember { mutableStateOf(false) }
    var priority by remember { mutableStateOf(WishPriority.Medium) }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add a Wish") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Text("←")
                    }
                }
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = isManualMode,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Button(
                        onClick = {
                            if (wishName.isBlank()) return@Button

                            viewModel.addWish(
                                Wish(
                                    wishName = wishName.trim(),
                                    price = price.ifBlank { "" },
                                    productLink = productLink.ifBlank { productUrl },
                                    wishListType = selectedWishList,
                                    priority = priority.name,
                                    notes = notes
                                )
                            )
                            onBackPressed()
                        },
                        enabled = wishName.isNotBlank(),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!isManualMode) {
                // URL-first entry
                Text(
                    text = "Paste a product link to auto-fill details",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = productUrl,
                    onValueChange = { productUrl = it },
                    label = { Text("Product Link") },
                    placeholder = { Text("https://...") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        // Auto-fill: copy URL to productLink field and switch to manual mode
                        productLink = productUrl
                        isManualMode = true
                        // TODO: trigger metadata fetch and populate fields
                    },
                    enabled = productUrl.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Find Product")
                }
                OutlinedButton(
                    onClick = { isManualMode = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Manually")
                }
            } else {
                // Manual / auto-filled form
                OutlinedTextField(
                    value = wishName,
                    onValueChange = { wishName = it },
                    label = { Text("Wish Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = productLink,
                    onValueChange = { productLink = it },
                    label = { Text("Product Link") },
                    placeholder = { Text("https://...") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Wish list type dropdown
                Box {
                    OutlinedTextField(
                        value = selectedWishList,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Add to Wish List") },
                        trailingIcon = {
                            IconButton(onClick = { dropdownExpanded = true }) {
                                Text("▾")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        wishListTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    selectedWishList = type
                                    dropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Priority chips
                Text(
                    text = "Priority",
                    style = MaterialTheme.typography.labelLarge
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    WishPriority.entries.forEach { p ->
                        FilterChip(
                            selected = priority == p,
                            onClick = { priority = p },
                            label = { Text(p.name) }
                        )
                    }
                }

                // Optional notes
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes (Optional)") },
                    placeholder = { Text("Add any extra details…") },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )

                // Bottom spacer so Save button doesn't overlap content
                Spacer(modifier = Modifier.height(8.dp))


            }
        }
    }
}

@Composable
fun TempScreen(modifier: Modifier = Modifier,
               text: String = "This is a temporary screen for testing purposes."
) {
     Text(
        text = text,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp))
}