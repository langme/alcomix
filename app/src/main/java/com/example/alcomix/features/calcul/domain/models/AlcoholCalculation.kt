package com.example.alcomix.features.calcul.domain.models

/**
 * Modèle de données pour les calculs d'alcool
 *
 * @property wineVolume Volume de vin en litres
 * @property wineDegree Degré d'alcool du vin
 * @property targetDegree Degré d'alcool cible
 * @property spiritVolume Volume d'eau-de-vie (optionnel)
 */
data class AlcoholCalculation(
    val wineVolume: Float = 0f,
    val wineDegree: Float = 0f,
    val targetDegree: Float = 0f,
    val spiritVolume: Float? = null
) {
    // Méthodes utilitaires si nécessaire
    fun isValid(): Boolean {
        return wineVolume > 0 &&
                wineDegree > 0 &&
                targetDegree > 0
    }
}