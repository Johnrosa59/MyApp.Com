package com.joaorosa.myappcom.domain.repository

import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.model.User

interface UserProfileRepository {
    suspend fun getUserProfile(userId: String): Resource<User>
    suspend fun saveUserProfile(user: User): Resource<Unit>
}
