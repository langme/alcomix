package com.langme.alcomix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.langme.alcomix.core.presentation.screens.SplashScreen
import com.langme.alcomix.theme.AlcomixTheme
import com.langme.alcomix.features.presentation.screens.AlcoholCalculatorScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlcomixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlcoholApp()
                }
            }
        }
    }
}

@Composable
fun AlcoholApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                navController = navController
            )
        }

        composable("calculator") {
            AlcoholCalculatorScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlcomixTheme {
        AlcoholCalculatorScreen()
    }
}