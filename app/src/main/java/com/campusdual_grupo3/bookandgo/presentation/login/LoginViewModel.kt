package com.campusdual_grupo3.bookandgo.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(


    private val userUseCase: UserUseCase

) : ViewModel() {

    private val _isLoggingSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isLoggingSuccess: LiveData<Boolean> = _isLoggingSuccess

    private val _isLoggingFormatValid: MutableLiveData<Boolean> = MutableLiveData()
    val isLoggingFormatValid: LiveData<Boolean> = _isLoggingFormatValid


    private var email: String = ""
    private var password: String = ""

    fun setEmail(email: String) {
        this.email = email
        userUseCase.isLoggingFormatValid(email, password)

    }

    fun setPassword(password: String) {
        this.password = password
        userUseCase.isLoggingFormatValid(email, password)
    }

    fun login() {
        val isMailValid = userUseCase.isMailValid(email)
        val isPasswordValid = userUseCase.isPasswordValid(password)
        if (isMailValid && isPasswordValid) {
            viewModelScope.launch {
                _isLoggingSuccess.value = userUseCase.login(email, password)
            }
        } else {
            _isLoggingFormatValid.value = false

        }
    }
    fun recoverPassword(){
        viewModelScope.launch {
            userUseCase.recoverPassword(email)
        }

    }

}