package com.joaorosa.myappcom.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.usecase.GetUserProfileUseCase
import com.joaorosa.myappcom.domain.usecase.SaveUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _userProfileState = MutableLiveData<User>()
    val userProfileState: LiveData<User> get() = _userProfileState

    private val _saveSuccess = MutableLiveData<Unit>()
    val saveSuccess: LiveData<Unit> get() = _saveSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loadProfile() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val user = getUserProfileUseCase.executeFetchProfile()
                _userProfileState.value = user
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Erro ao carregar perfil."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateProfile(user: User) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                saveUserProfileUseCase.executeSaveProfile(user)
                _saveSuccess.value = Unit
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Erro ao salvar perfil."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}
