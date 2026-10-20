package com.joaorosa.myappcom.domain.repository

import com.joaorosa.myappcom.domain.model.User

interface UserProfileRepository {
    suspend fun getUserProfile(userId: String): User
    suspend fun saveUserProfile(user: User)
}
