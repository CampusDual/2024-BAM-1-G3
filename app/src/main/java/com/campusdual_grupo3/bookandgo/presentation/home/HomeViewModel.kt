package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categories: List<CategoryEntity?> = listOf(null),
    val selectedCategory: CategoryEntity? = null,
    val experiences: List<ExperienceEntity> = emptyList(),
    val betterExperience: List<ExperienceEntity> = emptyList(),

    )

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val experiencesUseCase: ExperiencesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {

        viewModelScope.launch {
            val experiences = experiencesUseCase.getExperiences()

            _uiState.update { home ->
                home.copy(
                    experiences = experiences.sortedByDescending { experience ->
                            experience.createAt
                        }
                )
            }

            val betterExperience = experiencesUseCase.getExperiences()
            _uiState.update { valoradas ->
                valoradas.copy(
                    betterExperience = betterExperience.sortedByDescending { experience ->
                            experience.reviews.map { review -> review.rating }.average()

                        }
                )
            }

            val categories = experiencesUseCase.getCategories()
            _uiState.update { home ->
                home.copy(
                    categories = home.categories.plus(categories)
                )
            }
        }
    }

    fun onFavoriteClicked(experience: ExperienceEntity) {
        viewModelScope.launch {
            experience.isFavorite = !experience.isFavorite
            if (experience.isFavorite) {
                experiencesUseCase.addFavorite(experience)
            } else {
                experiencesUseCase.removeFavorite(experience)
            }
        }
    }

    fun onCategorySelected(category: CategoryEntity?) {
        _uiState.value = _uiState.value.copy(
            selectedCategory = category
        )
        if (category == null) {
            viewModelScope.launch {
                val experiences = experiencesUseCase.getExperiences()
                _uiState.update { home ->
                    home.copy(
                        experiences = experiences.sortedByDescending { it.createAt }
                    )
                }
                _uiState.update { valoradas ->
                    valoradas.copy(
                        betterExperience = experiences.sortedByDescending { experience ->
                            experience.reviews?.map { review -> review.rating }?.average() ?: 0.0

                        }
                    )
                }
            }

        } else {
            loadExperiencesByCategory(category.id)
        }

    }


    fun loadExperiencesByCategory(categoryId: Int) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoading = true
            )
            val experiences = experiencesUseCase.getExperiencesByCategory(categoryId)
            _uiState.update { home ->
                home.copy(
                    experiences = experiences
                )
            }

            _uiState.update { valoradas ->
                valoradas.copy(
                    betterExperience = experiences.sortedByDescending { experience ->
                        experience.reviews?.map { review -> review.rating }?.average() ?: 0.0
                    }
                )
            }

            _uiState.value = uiState.value.copy(
                isLoading = false
            )


        }
    }
}