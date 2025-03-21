package com.campusdual_grupo3.bookandgo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categories: List<CategoryEntity> = emptyList(),
    val selectedCategory: CategoryEntity? = null,
    val experiences: List<ExperienceEntity> = emptyList(),
    val betterExperience: List<ExperienceEntity> = emptyList(),
    val favorites: List<ExperienceEntity> = emptyList(),


    )

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val experiencesUseCase: ExperiencesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {

        viewModelScope.launch {

            val categories = experiencesUseCase.getCategories()
            _uiState.update { home ->
                home.copy(
                    categories = categories
                )
                }
            if (categories.isNotEmpty()) {
                onCategorySelected(categories.first().id)

            }
            val experiences = experiencesUseCase.getExperiences()
            _uiState.update { home ->
                home.copy(
                    experiences = experiences.sortedByDescending { experience ->
                        experience.createdAt
                    }
                )
            }


            val betterExperience = experiencesUseCase.getExperiences()
            _uiState.update { valoradas ->
                valoradas.copy(
                    betterExperience = betterExperience.sortedByDescending { experience ->
                        experience.reviews?.map { review -> review.rating }?.average()

                    }
                )
            }




            val favorit = experiencesUseCase.getFavorites()
            _uiState.update {
                it.copy(
                    favorites = favorit
                )
            }


        }
    }


    fun onFavoriteClicked(experience: ExperienceEntity) {
        // Actualizar el estado de favorito en la experiencia
        viewModelScope.launch(Dispatchers.IO) {
            experience.isFavorite = !experience.isFavorite
            if (experience.isFavorite) {
                experiencesUseCase.addFavorite(experience)
            } else {
                experiencesUseCase.removeFavorite(experience)
            }

            // Actualizar el estado de favorito en el estado global
            _uiState.value = _uiState.value.copy(
                favorites = _uiState.value.favorites.map {
                    if (it.id == experience.id) {
                        experience.copy(isFavorite = !experience.isFavorite)
                    } else {
                        it
                    }
                }
            )
        }

    }

    fun onCategorySelected(categoryId: Int) {
        val selectedCategory = uiState.value.categories.find { it.id == categoryId }
        _uiState.value = _uiState.value.copy(
            selectedCategory = selectedCategory
        )
        if (categoryId == -1) {
            viewModelScope.launch {
                val experiences = experiencesUseCase.getExperiencesByCategory(categoryId)
                _uiState.update { home ->
                    home.copy(
                        experiences = experiences.sortedByDescending { it.createdAt }
                    )
                }
                _uiState.update { valoradas ->
                    valoradas.copy(
                        betterExperience = experiences.sortedByDescending { experience ->
                            experience.reviews?.map { review -> review.rating }?.average()
                                ?: 0.0

                        }
                    )
                }
            }

        } else {
            loadExperiencesByCategory(categoryId)
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