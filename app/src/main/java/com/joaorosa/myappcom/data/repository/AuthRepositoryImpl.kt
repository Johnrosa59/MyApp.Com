package com.joaorosa.myappcom.data.repository

import com.joaorosa.myappcom.data.remote.firebase.FirebaseAuthSource
import com.joaorosa.myappcom.data.remote.firebase.FirestoreSource
import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authSource: FirebaseAuthSource,
    private val firestoreSource: FirestoreSource
) : AuthRepository {

    override fun isUserLoggedIn(): Boolean {
        return authSource.getCurrentUser() != null
    }

    override fun getCurrentUserId(): String? {
        return authSource.getCurrentUserId()
    }

    override suspend fun loginUser(emailInput: String, passwordInput: String): Resource<String> {
        return try {
            val userId = authSource.loginWithEmailAndPassword(emailInput, passwordInput)
            Resource.Success(userId)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Failed to log in.")
        }
    }

    override suspend fun registerUser(
        emailInput: String,
        passwordInput: String,
        nameInput: String
    ): Resource<String> {
        return try {
            val userId = authSource.registerWithEmailAndPassword(emailInput, passwordInput)
            val newUser = User(
                id = userId,
                name = nameInput,
                email = emailInput
            )
            firestoreSource.saveUserProfile(newUser)
            Resource.Success(userId)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Failed to register user.")
        }
    }

    override suspend fun resetPassword(emailInput: String): Resource<Unit> {
        return try {
            authSource.sendPasswordResetEmail(emailInput)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Failed to send password reset email.")
        }
    }

    override fun logoutUser() {
        authSource.logoutUser()
    }
}
