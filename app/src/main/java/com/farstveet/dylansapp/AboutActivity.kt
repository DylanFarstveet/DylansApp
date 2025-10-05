/*
 * Copyright (c) 2025 Dylan R. Farstveet
 * All rights reserved
 */

package com.farstveet.dylansapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                            title = { Text("About Dylan's App", modifier = Modifier.semantics { heading() }) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                ) { innerPadding ->
                    AboutAppScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AboutAppScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val versionName = remember {
        context.packageManager.getPackageInfo(context.packageName, 0).versionName
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = "App icon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = "Dylan’s App",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Version $versionName",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Copyright © 2025 Dylan R. Farstveet\nAll rights reserved.\n\nMY LITTLE PONY and all related characters are trademarks of Hasbro, Inc. This app is a non-commercial project created under the principles of fair use and is not affiliated with or endorsed by Hasbro.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 0.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("App Info", fontSize = 18.sp)
        }
    }
}
