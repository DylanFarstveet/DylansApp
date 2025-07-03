/*
 * Copyright (c) 2025 Dylan R. Farstveet
 * All rights reserved
 */

package com.farstveet.dylansapp

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farstveet.dylansapp.ui.theme.DylansAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DylansAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Dylan's App") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                ) { innerPadding ->
                    MainMenuScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainMenuScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BigButton("About Dylan") {
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }

        BigButton("Ponies") {
            val intent = Intent(context, PoniesActivity::class.java)
            context.startActivity(intent)
        }

        BigButton("Elements of Harmony") {
            val intent = Intent(context, ElementsActivity::class.java)
            context.startActivity(intent)
        }
    }
}

@Composable
fun BigButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text(text = text, fontSize = 20.sp)
    }
}
