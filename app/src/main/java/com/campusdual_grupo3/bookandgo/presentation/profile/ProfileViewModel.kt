package com.campusdual_grupo3.bookandgo.presentation.profile

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.UserRemoteDataSourceImpl
import com.campusdual_grupo3.bookandgo.data.datasource.remote.user.dto.UserDTO
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
    private val userRemoteDataSource: UserRemoteDataSourceImpl
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserDTO?>(null)
    val userProfile: StateFlow<UserDTO?> = _userProfile

    fun loadUserProfile() {
        viewModelScope.launch {
            _userProfile.value = userRemoteDataSource.getUserProfile()
        }
    }
    fun logout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            val success = userRemoteDataSource.logout()
            if (success) {
                onLogoutSuccess()
            }
        }
    }

}