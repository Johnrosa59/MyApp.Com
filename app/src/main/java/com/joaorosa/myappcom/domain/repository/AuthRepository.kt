package com.joaorosa.myappcom.domain.repository

import com.joaorosa.myappcom.domain.model.Resource

interface AuthRepository {
    fun isUserLoggedIn(): Boolean
    fun getCurrentUserId(): String?
    suspend fun loginUser(emailInput: String, passwordInput: String): Resource<String>
    suspend fun registerUser(emailInput: String, passwordInput: String, nameInput: String): Resource<String>
    suspend fun resetPassword(emailInput: String): Resource<Unit>
    fun logoutUser(): Unit
}
