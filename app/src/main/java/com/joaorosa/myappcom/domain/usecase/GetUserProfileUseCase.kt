package com.joaorosa.myappcom.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.UserProfileRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun executeFetchProfile(): User {
        val currentUid = firebaseAuth.currentUser?.uid
            ?: throw Exception("Usuário não autenticado.")
        return userProfileRepository.getUserProfile(currentUid)
    }
}
