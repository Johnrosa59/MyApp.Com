package com.joaorosa.myappcom.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.joaorosa.myappcom.domain.usecase.LoginUseCase
import com.joaorosa.myappcom.domain.usecase.RegisterUseCase
import com.joaorosa.myappcom.domain.usecase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginState = MutableLiveData<String>()
    val loginState: LiveData<String> get() = _loginState

    private val _registerState = MutableLiveData<String>()
    val registerState: LiveData<String> get() = _registerState

    private val _resetPasswordState = MutableLiveData<Unit>()
    val resetPasswordState: LiveData<Unit> get() = _resetPasswordState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun loginUser(emailInput: String, passwordInput: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val userId = loginUseCase.executeLogin(emailInput, passwordInput)
                _loginState.value = userId
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Erro ao fazer login."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun registerUser(emailInput: String, passwordInput: String, nameInput: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val userId = registerUseCase.executeRegister(emailInput, passwordInput, nameInput)
                _registerState.value = userId
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Erro ao cadastrar."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun sendPasswordReset(emailInput: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                resetPasswordUseCase.executeResetPassword(emailInput)
                _resetPasswordState.value = Unit
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Erro ao redefinir senha."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
