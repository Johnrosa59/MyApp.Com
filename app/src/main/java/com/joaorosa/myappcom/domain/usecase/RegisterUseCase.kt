package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun executeRegister(
        emailInput: String,
        passwordInput: String,
        nameInput: String
    ): String {
        if (nameInput.isEmpty()) {
            throw IllegalArgumentException("Informe o nome.")
        }
        if (emailInput.isEmpty()) {
            throw IllegalArgumentException("Informe o e-mail.")
        }
        if (passwordInput.length < 6) {
            throw IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.")
        }
        return authRepository.registerUser(emailInput, passwordInput, nameInput)
    }
}
