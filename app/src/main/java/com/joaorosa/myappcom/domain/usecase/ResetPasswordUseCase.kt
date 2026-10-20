package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun executeResetPassword(emailInput: String) {
        if (emailInput.isEmpty()) {
            throw IllegalArgumentException("Informe o e-mail.")
        }
        authRepository.resetPassword(emailInput)
    }
}
