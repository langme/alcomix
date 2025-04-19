package com.example.alcomix.features.calcul.presentation.state
// État de l'UI pour le ViewModel
data class AlcoholCalculatorUiState(
    val wineVolume: String = "",
    val wineDegree: String = "",
    val targetDegree: String = "",
    val spiritVolume: String = "",
    val isCalculatingVolume: Boolean = true,
    val result: String? = null,
    val error: String? = null
)
