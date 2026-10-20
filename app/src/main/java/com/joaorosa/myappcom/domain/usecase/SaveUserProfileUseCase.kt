package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.UserProfileRepository
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) {
    suspend fun executeSaveProfile(user: User) {
        if (user.id.isEmpty()) {
            throw IllegalArgumentException("ID do usuário inválido.")
        }
        userProfileRepository.saveUserProfile(user)
    }
}
