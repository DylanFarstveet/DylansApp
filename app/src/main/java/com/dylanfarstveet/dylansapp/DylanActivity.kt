/*
 * Copyright (c) 2025 Dylan R. Farstveet
 * All rights reserved
 */

package com.dylanfarstveet.dylansapp

import android.content.Intent
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
class DylanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DylansAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("About Dylan", modifier = Modifier.semantics { heading() }) },
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
                    AboutMeScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AboutMeScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("About Dylan", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.semantics { heading() })
        Text("Hey there! I'm Dylan, a 24-year-old who loves My Little Pony, music, and video games. I work at Target and enjoy creating music in my free time.")

        Text("Socials", style = MaterialTheme.typography.titleMedium, modifier = Modifier.semantics { heading() })
        LabeledLink("Twitter:", "@DFarstveet", "https://twitter.com/DFarstveet")
        LabeledLink("Instagram:", "Dylan_Farstveet_", "https://www.instagram.com/Dylan_Farstveet_")
        Text("Discord: dyllpyckle")
        LabeledLink("Email:", "dylanfarstveet512@gmail.com", "mailto:dylanfarstveet512@gmail.com")

        Text("Work", style = MaterialTheme.typography.titleMedium, modifier = Modifier.semantics { heading() })
        Text("I currently work at Target and previously worked at Walmart. I have experience in retail and customer service.")

        Text("Contact", style = MaterialTheme.typography.titleMedium, modifier = Modifier.semantics { heading() })
        Text("Feel free to reach out to me through my Discord or email.")

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun LabeledLink(label: String, linkText: String, url: String) {
    val context = LocalContext.current

    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = label)

        AndroidView(
            factory = {
                TextView(it).apply {
                    val spannable = SpannableString(linkText)
                    spannable.setSpan(
                        object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                context.startActivity(
                                    Intent(Intent.ACTION_VIEW, url.toUri())
                                )
                            }

                            override fun updateDrawState(ds: TextPaint) {
                                ds.isUnderlineText = true
                                ds.color = 0xFF0000EE.toInt()
                            }
                        },
                        0,
                        linkText.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    text = spannable
                    movementMethod = LinkMovementMethod.getInstance()
                    highlightColor = android.graphics.Color.TRANSPARENT
                }
            }
        )
    }
}

