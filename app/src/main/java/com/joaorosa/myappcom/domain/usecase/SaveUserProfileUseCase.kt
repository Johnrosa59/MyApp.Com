package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.UserProfileRepository
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) {
    suspend fun executeSaveProfile(user: User): Resource<Unit> {
        if (user.id.isBlank()) {
            return Resource.Error("User ID cannot be empty.")
        }
        return userProfileRepository.saveUserProfile(user)
    }
}
