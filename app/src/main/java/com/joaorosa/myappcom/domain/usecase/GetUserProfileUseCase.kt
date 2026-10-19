package com.joaorosa.myappcom.domain.usecase

import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.AuthRepository
import com.joaorosa.myappcom.domain.repository.UserProfileRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userProfileRepository: UserProfileRepository
) {
    suspend fun executeFetchProfile(): Resource<User> {
        val currentUserId = authRepository.getCurrentUserId()
            ?: return Resource.Error("User not authenticated.")
        return userProfileRepository.getUserProfile(currentUserId)
    }
}
