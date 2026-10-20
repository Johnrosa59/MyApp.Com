package com.joaorosa.myappcom.domain.repository

interface AuthRepository {
    suspend fun loginUser(emailInput: String, passwordInput: String): String
    suspend fun registerUser(emailInput: String, passwordInput: String, nameInput: String): String
    suspend fun resetPassword(emailInput: String)
}
