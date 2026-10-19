package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun executeResetPassword(emailInput: String): Resource<Unit> {
        if (emailInput.isBlank()) {
            return Resource.Error("Email address cannot be empty.")
        }
        return authRepository.resetPassword(emailInput)
    }
}
