package com.joaorosa.myappcom.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.AuthRepository
import com.joaorosa.myappcom.domain.usecase.GetUserProfileUseCase
import com.joaorosa.myappcom.domain.usecase.SaveUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val saveUserProfileUseCase: SaveUserProfileUseCase
) : ViewModel() {

    private val _userProfileState: MutableLiveData<Resource<User>> = MutableLiveData()
    val userProfileState: LiveData<Resource<User>> get() = _userProfileState

    private val _saveState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val saveState: LiveData<Resource<Unit>> get() = _saveState

    fun loadProfile() {
        _userProfileState.value = Resource.Loading
        viewModelScope.launch {
            val result = getUserProfileUseCase.executeFetchProfile()
            _userProfileState.postValue(result)
        }
    }

    fun updateProfile(user: User) {
        _saveState.value = Resource.Loading
        viewModelScope.launch {
            val result = saveUserProfileUseCase.executeSaveProfile(user)
            _saveState.postValue(result)
        }
    }

    fun logout() {
        authRepository.logoutUser()
    }
}
