package com.campusdual_grupo3.bookandgo.presentation.register.fragments.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.PreferencesEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PreferencesUistate(
    val preferences: List<CategoryEntity> = emptyList(),
    val availabilities: List<CategoryEntity> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val experiencesUseCase: ExperiencesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(PreferencesUistate())
    val uiState: StateFlow<PreferencesUistate> = _uiState.asStateFlow()

    private val preferencesSelected: MutableList<CategoryEntity> = mutableListOf()

    val getSelectedPreferences = preferencesSelected.joinToString("," )

    fun loadCategories() {
        viewModelScope.launch {
            val categories = experiencesUseCase.getCategories()
            _uiState.update {
                it.copy(
                    preferences = categories
                )
            }
        }
    }

    fun onSelectPreference(preference: CategoryEntity) {
        if (preferencesSelected.contains(preference)) {
            preferencesSelected.remove(preference)
        } else {
            preferencesSelected.add(preference)
        }
    }
}