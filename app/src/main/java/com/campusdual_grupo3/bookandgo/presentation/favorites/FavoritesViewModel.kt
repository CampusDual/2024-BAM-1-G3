package com.campusdual_grupo3.bookandgo.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val favoritesExperiences: List<ExperienceEntity> = emptyList()
)

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val experiencesUseCase: ExperiencesUseCase

) : ViewModel() {

    private val _uiState: MutableStateFlow<FavoritesUiState> = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init {
        viewModelScope.launch {
            val favoritesExperiences = experiencesUseCase.getFavorites()
            _uiState.value = uiState.value.copy(
                favoritesExperiences = favoritesExperiences
            )
        }
    }
}
