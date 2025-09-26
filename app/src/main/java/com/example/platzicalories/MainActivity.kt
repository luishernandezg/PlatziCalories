package com.example.platzicalories

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.platzicalories.core.navigation.NavigationRoot
import com.example.platzicalories.ui.theme.PlatziCaloriesTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlatziCaloriesTheme {
                val navController = rememberNavController()
                val snackbarHostSate = remember { SnackbarHostState() }
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavigationRoot(
                        snackbarHostState = snackbarHostSate,
                        navHostController = navController
                    )
                }
            }
        }
    }
}