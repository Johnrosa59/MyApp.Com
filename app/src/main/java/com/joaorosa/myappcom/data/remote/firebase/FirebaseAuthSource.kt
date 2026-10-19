package com.joaorosa.myappcom.data.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    suspend fun loginWithEmailAndPassword(emailInput: String, passwordInput: String): String {
        val authResult = firebaseAuth.signInWithEmailAndPassword(emailInput, passwordInput).await()
        return authResult.user?.uid ?: throw IllegalStateException("User ID not found after login.")
    }

    suspend fun registerWithEmailAndPassword(emailInput: String, passwordInput: String): String {
        val authResult = firebaseAuth.createUserWithEmailAndPassword(emailInput, passwordInput).await()
        return authResult.user?.uid ?: throw IllegalStateException("User ID not found after registration.")
    }

    suspend fun sendPasswordResetEmail(emailInput: String) {
        firebaseAuth.sendPasswordResetEmail(emailInput).await()
    }

    fun logoutUser() {
        firebaseAuth.signOut()
    }
}
