package com.campusdual_grupo3.bookandgo.presentation.register.fragments.personalinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(val authUseCase: UserUseCase) : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = _name

    private val _phone: MutableLiveData<Boolean> = MutableLiveData()
    val phone: LiveData<Boolean> = _phone

    private val _zipcode: MutableLiveData<String> = MutableLiveData()
    val zipcode: LiveData<String> = _zipcode

    private val _address: MutableLiveData<String> = MutableLiveData()
    val address: LiveData<String> = _address

    private val _email: MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String> = _email

    private val _password: MutableLiveData<String> = MutableLiveData()
    val password: LiveData<String> = _password

    //tratamos los errores
    private val _errorName: MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> = _errorName

    private val _errorPhone: MutableLiveData<Boolean> = MutableLiveData()
    val errorPhone: LiveData<Boolean> = _errorPhone

    private val _errorAddress: MutableLiveData<Boolean> = MutableLiveData()
    val errorAddress: LiveData<Boolean> = _errorAddress

    private val _errorZipcode: MutableLiveData<Boolean> = MutableLiveData()
    val errorZipcode: LiveData<Boolean> = _errorZipcode

    private val _errorEmail: MutableLiveData<Boolean> = MutableLiveData()
    val errorEmail: LiveData<Boolean> = _errorEmail

    private val _errorPassword: MutableLiveData<Boolean> = MutableLiveData()
    val errorPassword: LiveData<Boolean> = _errorPassword

    private val _isRegisteredSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isRegisteredSuccess: LiveData<Boolean> = _isRegisteredSuccess

    private val _formHasError: MutableLiveData<Boolean> = MutableLiveData()
    val formHasError: LiveData<Boolean> = _formHasError

    fun setUsername(name: String) {
        _name.value = name
        _errorName.value = !authUseCase.isNameValid(name)
    }

    fun setAddress(address: String) {
        _address.value = address
        _errorAddress.value = !authUseCase.isAddressValid(address)
    }

    fun setZipcode(zipcode: String) {
        _zipcode.value = zipcode
        _errorZipcode.value = !authUseCase.isZipcodeValid(zipcode)
    }

    fun setPhone(phone: String) {
        _phone.value = phone.toBoolean()
        _errorPhone.value = !authUseCase.isPhoneValid(phone)
    }

    fun setEmail(email: String) {
        _email.value = email
        _errorEmail.value = !authUseCase.isMailValid(email)
    }

    fun setPassword(password: String) {
        _password.value = password
        _errorPassword.value = !authUseCase.isPasswordValid(password)
    }

    fun isRegisterFormValid(user: UserEntity): Boolean {
        return authUseCase.isRegisterFormValid(user)
    }



}