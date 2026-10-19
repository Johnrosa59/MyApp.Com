package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun executeRegister(
        emailInput: String,
        passwordInput: String,
        nameInput: String
    ): Resource<String> {
        if (nameInput.isBlank()) {
            return Resource.Error("Name cannot be empty.")
        }
        if (emailInput.isBlank()) {
            return Resource.Error("Email address cannot be empty.")
        }
        if (passwordInput.length < 6) {
            return Resource.Error("Password must be at least 6 characters.")
        }
        return authRepository.registerUser(emailInput, passwordInput, nameInput)
    }
}
