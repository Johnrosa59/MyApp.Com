package com.joaorosa.myappcom.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.joaorosa.myappcom.domain.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun saveUserProfile(user: User) {
        val userMap = mapOf(
            "id" to user.id,
            "name" to user.name,
            "email" to user.email,
            "weightKg" to user.weightKg,
            "heightCm" to user.heightCm,
            "dailyCalorieGoal" to user.dailyCalorieGoal,
            "membershipStatus" to user.membershipStatus
        )
        firestore.collection("users").document(user.id).set(userMap).await()
    }

    suspend fun fetchUserProfile(userId: String): User? {
        val documentSnapshot = firestore.collection("users").document(userId).get().await()
        if (!documentSnapshot.exists()) {
            return null
        }
        return User(
            id = documentSnapshot.getString("id") ?: userId,
            name = documentSnapshot.getString("name") ?: "",
            email = documentSnapshot.getString("email") ?: "",
            weightKg = documentSnapshot.getDouble("weightKg") ?: 0.0,
            heightCm = documentSnapshot.getDouble("heightCm") ?: 0.0,
            dailyCalorieGoal = documentSnapshot.getLong("dailyCalorieGoal")?.toInt() ?: 2000,
            membershipStatus = documentSnapshot.getString("membershipStatus") ?: "Active"
        )
    }
}
