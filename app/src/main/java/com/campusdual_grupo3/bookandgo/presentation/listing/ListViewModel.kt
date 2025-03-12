package com.campusdual_grupo3.bookandgo.presentation.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val listAllExperiences: List<ExperienceEntity> = emptyList(),
    val detailExperiences: ExperienceEntity? = null

)
@HiltViewModel
class ListViewModel @Inject constructor(
    private val experiencesUseCase: ExperiencesUseCase

) : ViewModel() {


    private val _uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState

    init {
        viewModelScope.launch {
            val listAllExperiences = experiencesUseCase.getExperiences()
            _uiState.value = uiState.value.copy(
                listAllExperiences = listAllExperiences
            )
        }
    }


}