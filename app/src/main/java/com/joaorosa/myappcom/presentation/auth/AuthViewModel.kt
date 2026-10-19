package com.joaorosa.myappcom.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.repository.AuthRepository
import com.joaorosa.myappcom.domain.usecase.LoginUseCase
import com.joaorosa.myappcom.domain.usecase.RegisterUseCase
import com.joaorosa.myappcom.domain.usecase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _loginState: MutableLiveData<Resource<String>> = MutableLiveData()
    val loginState: LiveData<Resource<String>> get() = _loginState

    private val _registerState: MutableLiveData<Resource<String>> = MutableLiveData()
    val registerState: LiveData<Resource<String>> get() = _registerState

    private val _resetPasswordState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val resetPasswordState: LiveData<Resource<Unit>> get() = _resetPasswordState

    fun isUserLoggedIn(): Boolean {
        return authRepository.isUserLoggedIn()
    }

    fun loginUser(emailInput: String, passwordInput: String) {
        _loginState.value = Resource.Loading
        viewModelScope.launch {
            val result = loginUseCase.executeLogin(emailInput, passwordInput)
            _loginState.postValue(result)
        }
    }

    fun registerUser(emailInput: String, passwordInput: String, nameInput: String) {
        _registerState.value = Resource.Loading
        viewModelScope.launch {
            val result = registerUseCase.executeRegister(emailInput, passwordInput, nameInput)
            _registerState.postValue(result)
        }
    }

    fun sendPasswordReset(emailInput: String) {
        _resetPasswordState.value = Resource.Loading
        viewModelScope.launch {
            val result = resetPasswordUseCase.executeResetPassword(emailInput)
            _resetPasswordState.postValue(result)
        }
    }
}
