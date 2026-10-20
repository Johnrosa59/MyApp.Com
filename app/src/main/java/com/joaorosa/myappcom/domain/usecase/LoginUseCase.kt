package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun executeLogin(emailInput: String, passwordInput: String): String {
        if (emailInput.isEmpty()) {
            throw IllegalArgumentException("Informe o e-mail.")
        }
        if (passwordInput.isEmpty()) {
            throw IllegalArgumentException("Informe a senha.")
        }
        return authRepository.loginUser(emailInput, passwordInput)
    }
}
