package com.example.alcomix.features.calcul.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.alcomix.features.calcul.domain.usecases.CalculateAlcoholUseCase
import com.example.alcomix.features.calcul.domain.models.AlcoholCalculation
import com.example.alcomix.features.calcul.presentation.state.AlcoholCalculatorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlcoholCalculatorViewModel @Inject constructor(
    private val calculateAlcoholUseCase: CalculateAlcoholUseCase
) : ViewModel() {

    // État de l'UI
    private val _uiState = MutableStateFlow(AlcoholCalculatorUiState())
    val uiState: StateFlow<AlcoholCalculatorUiState> = _uiState.asStateFlow()

    // Mise à jour du volume de vin
    fun updateWineVolume(volume: String) {
        _uiState.update { it.copy(wineVolume = volume) }
    }

    // Mise à jour du degré de vin
    fun updateWineDegree(degree: String) {
        _uiState.update { it.copy(wineDegree = degree) }
    }

    // Mise à jour du degré cible
    fun updateTargetDegree(degree: String) {
        _uiState.update { it.copy(targetDegree = degree) }
    }

    // Mise à jour du volume d'eau-de-vie
    fun updateSpiritVolume(volume: String) {
        _uiState.update { it.copy(spiritVolume = volume) }
    }

    // Basculer entre les modes de calcul
    fun toggleCalculationMode() {
        _uiState.update {
            it.copy(isCalculatingVolume = !it.isCalculatingVolume)
        }
    }

    // Effectuer le calcul
    fun calculate() {
        try {
            val calculation = AlcoholCalculation(
                wineVolume = _uiState.value.wineVolume.toFloatOrNull() ?: 0f,
                wineDegree = _uiState.value.wineDegree.toFloatOrNull() ?: 0f,
                targetDegree = _uiState.value.targetDegree.toFloatOrNull() ?: 0f,
                spiritVolume = if (!_uiState.value.isCalculatingVolume)
                    _uiState.value.spiritVolume.toFloatOrNull()
                else null
            )

            val result = if (_uiState.value.isCalculatingVolume) {
                calculateAlcoholUseCase.calculateSpiritVolume(calculation)
            } else {
                calculateAlcoholUseCase.calculateFinalDegree(calculation)
            }

            _uiState.update {
                it.copy(
                    result = result.toString(),
                    error = null
                )
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    result = null,
                    error = "Erreur de calcul : ${e.localizedMessage}"
                )
            }
        }
    }

    // Réinitialiser le formulaire
    fun reset() {
        _uiState.value = AlcoholCalculatorUiState()
    }
}