package com.joaorosa.myappcom.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _userProfileState = MutableLiveData<User>()
    val userProfileState: LiveData<User> get() = _userProfileState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchProfileData() {
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
}
