/*
 * Copyright (c) 2025 Dylan R. Farstveet
 * All rights reserved
 */

package com.farstveet.dylansapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.farstveet.dylansapp.ui.theme.DylansAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DylansAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("About Dylan") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
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
        Text("About Dylan", style = MaterialTheme.typography.headlineMedium)
        Text("Hey there! I'm Dylan, a 24-year-old who loves My Little Pony, music, and video games. I work at Target and enjoy creating music in my free time.")

        Text("Socials", style = MaterialTheme.typography.titleMedium)
        LabeledLink("Twitter:", "@DFarstveet", "https://twitter.com/DFarstveet")
        LabeledLink("Instagram:", "Dylan_Farstveet_", "https://www.instagram.com/Dylan_Farstveet_")
        Text("Discord: dyllpyckle")
        LabeledLink("Email:", "dylanfarstveet512@gmail.com", "mailto:dylanfarstveet512@gmail.com")

        Text("Work", style = MaterialTheme.typography.titleMedium)
        Text("I currently work at Target and previously worked at Walmart. I have experience in retail and customer service.")

        Text("Contact", style = MaterialTheme.typography.titleMedium)
        Text("Feel free to reach out to me through my Discord or email.")

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun LabeledLink(label: String, linkText: String, url: String) {
    val context = LocalContext.current

    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = label)

        Text(
            text = linkText,
            color = Color(0xFF0000EE),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )
    }
}
