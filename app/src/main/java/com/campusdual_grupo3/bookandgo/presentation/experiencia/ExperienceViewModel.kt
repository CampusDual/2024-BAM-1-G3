package com.campusdual_grupo3.bookandgo.presentation.experiencia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ReviewEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExperienceUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detailExperiences: ExperienceEntity? = null,
    val id: Int? = null,
    val reviews: List<ReviewEntity?> = emptyList(),

    )


@HiltViewModel
class ExperienceViewModel @Inject constructor(
    private val experiencesUseCase: ExperiencesUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<ExperienceUiState> =
        MutableStateFlow(ExperienceUiState())
    val uiState: StateFlow<ExperienceUiState> = _uiState



    fun loadExperienceById(experienceId: Int) {
        viewModelScope.launch {
            val experience = experiencesUseCase.getExperienceById(experienceId)
            _uiState.value = uiState.value.copy(
                detailExperiences = experience
            )
        }

    }
    fun loadReviewsByExperienceId(experienceId: Int) {
        viewModelScope.launch {
            val reviews = experiencesUseCase.getRewiewsByExperienceId(experienceId)
            _uiState.value = uiState.value.copy(
                reviews = reviews
            )
        }
    }

    fun clearDetailExperience() {
        _uiState.update { currentState ->
            currentState.copy(detailExperiences = null)
        }
    }
}