package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun executeLogin(emailInput: String, passwordInput: String): Resource<String> {
        if (emailInput.isBlank()) {
            return Resource.Error("Email address cannot be empty.")
        }
        if (passwordInput.isBlank()) {
            return Resource.Error("Password cannot be empty.")
        }
        return authRepository.loginUser(emailInput, passwordInput)
    }
}
