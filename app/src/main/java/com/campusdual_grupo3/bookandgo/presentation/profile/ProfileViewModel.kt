package com.campusdual_grupo3.bookandgo.presentation.profile

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCase
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserEntity?>(null)
    val userProfile: StateFlow<UserEntity?> = _userProfile

    fun loadUserProfile() {
        viewModelScope.launch {
            _userProfile.value = userUseCase.getUserProfile()
        }
    }
    fun logout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            val success = userUseCase.logout()
            if (success) {
                onLogoutSuccess()
            }
        }
    }

}