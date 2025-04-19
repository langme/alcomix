package com.example.alcomix.features.calcul.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Liquor
import androidx.compose.material.icons.filled.WineBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.alcomix.features.calcul.domain.usecases.CalculateAlcoholUseCase
import com.example.alcomix.features.calcul.presentation.viewmodels.AlcoholCalculatorViewModel
import com.example.alcomix.features.calcul.presentation.widget.CalculationModeButton
import com.example.alcomix.ui.theme.AlcomixTheme
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlcoholCalculatorScreen(
    viewModel: AlcoholCalculatorViewModel = hiltViewModel()
) {
    // Collecte de l'état du ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "A toi de jouer ...",
                        style = MaterialTheme.typography.titleLarge.copy(
                            MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sélecteur de mode de calcul amélioré
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Bouton pour calculer le volume d'eau-de-vie
                    CalculationModeButton(
                        icon = Icons.Default.Liquor,
                        text = "Volume\nd'eau-de-vie",
                        isSelected = uiState.isCalculatingVolume,
                        onClick = { viewModel.toggleCalculationMode() }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    // Bouton pour calculer le degré final
                    CalculationModeButton(
                        icon = Icons.Default.WineBar,
                        text = "Degré\nfinal",
                        isSelected = !uiState.isCalculatingVolume,
                        onClick = { viewModel.toggleCalculationMode() }
                    )
                }
            }

            // Champs de saisie communs
            OutlinedTextField(
                value = uiState.wineVolume,
                onValueChange = { viewModel.updateWineVolume(it) },
                label = { Text("Volume de vin (L)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.wineDegree,
                onValueChange = { viewModel.updateWineDegree(it) },
                label = { Text("Degré du vin (%)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            if (uiState.isCalculatingVolume) {
                OutlinedTextField(
                    value = uiState.targetDegree,
                    onValueChange = { viewModel.updateTargetDegree(it) },
                    label = { Text("Degré cible (%)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Champ conditionnel basé sur le mode de calcul
            if (!uiState.isCalculatingVolume) {
                OutlinedTextField(
                    value = uiState.spiritVolume,
                    onValueChange = { viewModel.updateSpiritVolume(it) },
                    label = { Text("Volume d'eau-de-vie (L)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(Modifier.width(20.dp))

            // Bouton de calcul
            Button(
                onClick = { viewModel.calculate() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(
                    Icons.Default.Calculate,
                    contentDescription = "Calculer",
                    Modifier.size(48.dp)
                )
                Spacer(Modifier.width(20.dp))
                Text(
                    "Calculer",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            // Affichage du résultat
            if (uiState.result?.isNotEmpty() == true) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = uiState.result!!,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImprovedCalculatorPreview() {
    AlcomixTheme {
        val previewViewModel = AlcoholCalculatorViewModel(CalculateAlcoholUseCase())
        AlcoholCalculatorScreen(previewViewModel)
    }
}