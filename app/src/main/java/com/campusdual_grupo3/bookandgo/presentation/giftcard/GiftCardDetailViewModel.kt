package com.campusdual_grupo3.bookandgo.presentation.giftcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardMailDto
import com.campusdual_grupo3.bookandgo.domain.entities.ExperienceEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.experiences.ExperiencesUseCase
import com.campusdual_grupo3.bookandgo.domain.usecases.giftcard.GiftMailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detailExperiences: ExperienceEntity? = null,
    val id: Int? = null
)

@HiltViewModel
class GiftCardDetailViewModel @Inject constructor(
    private val giftMailUseCase: GiftMailUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<MailUiState> =
        MutableStateFlow(MailUiState())
    val uiState: StateFlow<MailUiState> = _uiState


    fun sendGiftCard(giftCardMailDto: GiftCardMailDto) {
        viewModelScope.launch {
            // Lógica para enviar la tarjeta de regalo
            giftMailUseCase.sendGiftCard(giftCardMailDto)
        }
    }
}