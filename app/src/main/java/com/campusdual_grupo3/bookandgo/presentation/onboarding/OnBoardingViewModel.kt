package com.campusdual_grupo3.bookandgo.presentation.onboarding


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.usecases.AppPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appPreferencesUseCase: AppPreferencesUseCase
) : ViewModel() {

    private var _shouldShowOnBoarding = MutableLiveData<Boolean>()
    var shouldShowOnBoarding : LiveData<Boolean> = _shouldShowOnBoarding

    init {
        viewModelScope.launch {
            _shouldShowOnBoarding.value = !appPreferencesUseCase.isOnboardingCompleted()
        }
    }

    fun markOnboardingAsCompleted() {
        viewModelScope.launch {
            appPreferencesUseCase.setOnboardingCompleted(true)
        }
    }
}