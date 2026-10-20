package com.joaorosa.myappcom.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.joaorosa.myappcom.data.remote.firebase.FirestoreSource
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestoreSource: FirestoreSource
) : AuthRepository {

    override suspend fun loginUser(emailInput: String, passwordInput: String): String {
        val result = firebaseAuth.signInWithEmailAndPassword(emailInput, passwordInput).await()
        return result.user?.uid ?: throw Exception("Usuário não encontrado.")
    }

    override suspend fun registerUser(
        emailInput: String,
        passwordInput: String,
        nameInput: String
    ): String {
        val result = firebaseAuth.createUserWithEmailAndPassword(emailInput, passwordInput).await()
        val userId = result.user?.uid ?: throw Exception("Erro ao criar usuário.")
        val newUser = User(
            id = userId,
            name = nameInput,
            email = emailInput
        )
        firestoreSource.saveUserProfile(newUser)
        return userId
    }

    override suspend fun resetPassword(emailInput: String) {
        firebaseAuth.sendPasswordResetEmail(emailInput).await()
    }
}
