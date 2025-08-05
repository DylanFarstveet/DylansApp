/*
 * Copyright (c) 2025 Dylan R. Farstveet
 * All rights reserved
 */

package com.farstveet.dylansapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.farstveet.dylansapp.ui.theme.DylansAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class PoniesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DylansAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Ponies", modifier = Modifier.semantics { heading() }) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                ) { innerPadding ->
                    PoniesScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PoniesScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PonyCard("Twilight Sparkle", "https://mlp.fandom.com/wiki/Twilight_Sparkle")
        PonyCard("Rainbow Dash", "https://mlp.fandom.com/wiki/Rainbow_Dash")
        PonyCard("Applejack", "https://mlp.fandom.com/wiki/Applejack")
        PonyCard("Rarity", "https://mlp.fandom.com/wiki/Rarity")
        PonyCard("Fluttershy", "https://mlp.fandom.com/wiki/Fluttershy")
        PonyCard("Pinkie Pie", "https://mlp.fandom.com/wiki/Pinkie_Pie")
    }
}

@Composable
fun PonyCard(name: String, link: String) {
    val context = LocalContext.current
    Column {
        Text(text = name, style = MaterialTheme.typography.titleLarge, modifier = Modifier.semantics { heading() })
        Text(
            text = "Learn more here",
            color = Color(0xFF0000EE), // link-style color
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                openInCustomTab(context, link)
            }
        )
    }
}

fun openInCustomTab(context: Context, url: String) {
    val intent = CustomTabsIntent.Builder().build()
    intent.launchUrl(context, Uri.parse(url))
}
