package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var items by rememberSaveable { mutableStateOf(listOf<Int>()) }

    val configuration = LocalConfiguration.current
    val columns = if (configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
        4
    } else {
        3
    }

    val systemInsets = WindowInsets.systemBars
        .union(WindowInsets.displayCutout)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(systemInsets)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                if (index % 2 == 0) MaterialTheme.colorScheme.primaryContainer
                                else MaterialTheme.colorScheme.secondaryContainer
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$index",
                            color = if (index % 2 == 0) MaterialTheme.colorScheme.onPrimaryContainer
                            else MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { items = items + items.size },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add item"
            )
        }
    }
}