package com.campusdual_grupo3.bookandgo.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val favoritesExperiences: List<ExperienceEntity> = emptyList(),
    val favorite: ExperienceEntity?  = null,
    // Otros estados y funciones de la UI aquí
    val favorites: List<ExperienceEntity> = emptyList()


)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val experiencesUseCase: ExperiencesUseCase

) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesUiState())

    // private val _uiState: MutableStateFlow<FavoritesUiState> = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        loadFavorites()
    }
    fun addFavorite(experience: ExperienceEntity) {
        viewModelScope.launch {
            experiencesUseCase.addFavorite(experience)
            loadFavorites() // Actualiza la lista después de agregar un favorito
        }
    }

    fun removeFavorite(experience: ExperienceEntity) {
        viewModelScope.launch {
            experiencesUseCase.removeFavorite(experience)
            loadFavorites() // Actualiza la lista después de eliminar un favorito
        }
    }


    fun toggleFavorite(experience: ExperienceEntity) {
        viewModelScope.launch {
            // Simulamos la operación de agregar/eliminar favorito
            val updatedFavorites = _uiState.value.favoritesExperiences.toMutableList()

            if (!updatedFavorites.contains(experience)) {
                updatedFavorites.remove(experience)
            } else {
                updatedFavorites.add(experience)
            }

            _uiState.value = _uiState.value.copy(favoritesExperiences = updatedFavorites)
        }
    }


    private fun loadFavorites() {
        viewModelScope.launch {
            val favoritesExperiences = experiencesUseCase.getFavorites()
            _uiState.value = _uiState.value.copy(favoritesExperiences = favoritesExperiences)
        }
    }
}
