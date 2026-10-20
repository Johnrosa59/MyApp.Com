package com.joaorosa.myappcom.data.repository

import com.joaorosa.myappcom.data.remote.firebase.FirestoreSource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val firestoreSource: FirestoreSource
) : UserProfileRepository {

    override suspend fun getUserProfile(userId: String): User {
        return firestoreSource.fetchUserProfile(userId)
            ?: throw Exception("Perfil do usuário não encontrado.")
    }

    override suspend fun saveUserProfile(user: User) {
        firestoreSource.saveUserProfile(user)
    }
}
