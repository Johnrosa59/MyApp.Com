package com.joaorosa.myappcom.data.repository

import com.joaorosa.myappcom.data.remote.firebase.FirestoreSource
import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val firestoreSource: FirestoreSource
) : UserProfileRepository {

    override suspend fun getUserProfile(userId: String): Resource<User> {
        return try {
            val user = firestoreSource.fetchUserProfile(userId)
            if (user != null) {
                Resource.Success(user)
            } else {
                Resource.Error("User profile not found in Firestore.")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Failed to fetch user profile.")
        }
    }

    override suspend fun saveUserProfile(user: User): Resource<Unit> {
        return try {
            firestoreSource.saveUserProfile(user)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Failed to save user profile.")
        }
    }
}
