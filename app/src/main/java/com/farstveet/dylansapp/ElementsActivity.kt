/*
 * Copyright (c) 2025 Dylan R. Farstveet
 * All rights reserved
 */

package com.farstveet.dylansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.farstveet.dylansapp.ui.theme.DylansAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class ElementsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DylansAppTheme {
                var currentElement by remember { mutableStateOf<String?>(null) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Elements of Harmony", modifier = Modifier.semantics { heading() }) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Click on a pony’s name to see their Element of Harmony!",
                            fontSize = 18.sp
                        )

                        HarmonyButton("Twilight Sparkle") { currentElement = "Magic" }
                        HarmonyButton("Applejack") { currentElement = "Honesty" }
                        HarmonyButton("Rainbow Dash") { currentElement = "Loyalty" }
                        HarmonyButton("Pinkie Pie") { currentElement = "Laughter" }
                        HarmonyButton("Rarity") { currentElement = "Generosity" }
                        HarmonyButton("Fluttershy") { currentElement = "Kindness" }
                    }

                    if (currentElement != null) {
                        AlertDialog(
                            onDismissRequest = { currentElement = null },
                            confirmButton = {
                                TextButton(onClick = { currentElement = null }) {
                                    Text("OK")
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            title = { Text("Element of Harmony") },
                            text = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$currentElement",
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                }
                            }

                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HarmonyButton(name: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(text = name)
    }
}
