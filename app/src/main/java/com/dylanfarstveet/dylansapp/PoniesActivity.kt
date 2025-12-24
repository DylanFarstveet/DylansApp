/*
 * Copyright (c) 2025 Dylan R. Farstveet
 * All rights reserved
 */

package com.dylanfarstveet.dylansapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.dylanfarstveet.dylansapp.ui.theme.DylansAppTheme

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
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Navigate up"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
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

        AndroidView(
            factory = {
                TextView(it).apply {
                    val text = "Learn more here"
                    val spannable = SpannableString(text)
                    spannable.setSpan(
                        object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                openInCustomTab(context, link)
                            }

                            override fun updateDrawState(ds: TextPaint) {
                                ds.isUnderlineText = true
                                ds.color = 0xFF0000EE.toInt()
                            }
                        },
                        0,
                        text.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setText(spannable, TextView.BufferType.SPANNABLE)
                    movementMethod = LinkMovementMethod.getInstance()
                    highlightColor = android.graphics.Color.TRANSPARENT
                }
            }
        )
    }
}

fun openInCustomTab(context: Context, url: String) {
    val intent = CustomTabsIntent.Builder().build()
    intent.launchUrl(context, Uri.parse(url))
}
