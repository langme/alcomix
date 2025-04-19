package com.example.alcomix.features.calcul.domain.usecases

import com.example.alcomix.features.calcul.domain.models.AlcoholCalculation

class CalculateAlcoholUseCase {
    /**
     * Calcule le volume d'eau-de-vie nécessaire pour atteindre un degré cible
     *
     * @param calculation Paramètres de calcul
     * @return Volume d'eau-de-vie en litres
     */
    fun calculateSpiritVolume(calculation: AlcoholCalculation): Float {
        require(calculation.wineVolume > 0) { "Volume de vin doit être positif" }
        require(calculation.wineDegree > 0) { "Degré du vin doit être positif" }
        require(calculation.targetDegree > calculation.wineDegree) { "Degré cible doit être supérieur au degré du vin" }

        val spiritVolume = ((calculation.targetDegree * calculation.wineVolume -
                calculation.wineVolume * calculation.wineDegree) /
                (40f - calculation.targetDegree))  // Utiliser 40° comme degré standard de l'eau-de-vie
            .roundToTwoDecimals()

        return spiritVolume
    }

    /**
     * Calcule le degré final du mélange
     *
     * @param calculation Paramètres de calcul
     * @return Degré final du mélange
     */
    fun calculateFinalDegree(calculation: AlcoholCalculation): Float {
        require(calculation.wineVolume > 0) { "Volume de vin doit être positif" }
        require(calculation.wineDegree > 0) { "Degré du vin doit être positif" }
        require(calculation.spiritVolume != null && calculation.spiritVolume > 0) { "Volume d'eau-de-vie doit être positif" }

        val finalDegree = ((calculation.wineVolume * calculation.wineDegree +
                calculation.spiritVolume * 40f) /
                (calculation.wineVolume + calculation.spiritVolume))
            .roundToTwoDecimals()

        return finalDegree
    }

    // Extension function pour arrondir à deux décimales
    private fun Float.roundToTwoDecimals(): Float {
        return "%.2f".format(this).replace(',', '.').toFloat()
    }
}