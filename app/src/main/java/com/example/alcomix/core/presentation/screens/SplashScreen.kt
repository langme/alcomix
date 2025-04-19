package com.example.alcomix.core.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.alcomix.R

@Composable
fun SplashScreen(
    navController: NavController
) {
    // Animation de mise à l'échelle
    val scale = remember { Animatable(0.5f) }

    // Couleurs personnalisées
    val backgroundColor = MaterialTheme.colorScheme.primary
    val textColor = MaterialTheme.colorScheme.onPrimary

    // Lance l'animation et la navigation
    LaunchedEffect(true) {
        // Animation de mise à l'échelle
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )

        // Délai avant de naviguer
        delay(2000)

        // Naviguer vers l'écran principal
        navController.navigate("calculator") {
            // Permet de ne pas garder l'écran de splash dans la pile de navigation
            popUpTo("splash") { inclusive = true }
        }
    }

    // Disposition du Splash Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo ou icône principale
            Image(
                painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale.value)
            )

            // Espace entre le logo et le texte
            Spacer(modifier = Modifier.height(16.dp))

            // Nom de l'application
            Text(
                text = "AlcoCalc",
                color = textColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.scale(scale.value)
            )

            // Sous-titre
            Text(
                text = "Calculateur d'Alcool Maison",
                color = textColor.copy(alpha = 0.8f),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .scale(scale.value)
            )
        }
    }
}