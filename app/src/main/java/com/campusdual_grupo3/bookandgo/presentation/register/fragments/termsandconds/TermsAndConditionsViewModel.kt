package com.campusdual_grupo3.bookandgo.presentation.register.fragments.termsandconds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCase
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TermsAndConditionsViewModel @Inject constructor(private val authUseCase: UserUseCase): ViewModel() {

    private val _isRegisteredSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isRegisteredSuccess: LiveData<Boolean> = _isRegisteredSuccess

    private val _formHasError: MutableLiveData<Boolean> = MutableLiveData()
    val formHasError: LiveData<Boolean> = _formHasError

    fun isRegisterFormValid(user: UserEntity): Boolean {
        return authUseCase.isRegisterFormValid(user)
    }

     fun register(user: UserEntity) {
         viewModelScope.launch {
             if(isRegisterFormValid(user)) {
                 val (isSuccess, message) = authUseCase.register(user) // Extrae los valores del Pair
                 _isRegisteredSuccess.value = isSuccess // Asigna solo el Boolean
             } else {
                 _formHasError.value = true
             }
         }
    }
}