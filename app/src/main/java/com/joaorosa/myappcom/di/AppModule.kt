package com.joaorosa.myappcom.di

import com.joaorosa.myappcom.data.repository.AuthRepositoryImpl
import com.joaorosa.myappcom.data.repository.NutritionRepositoryImpl
import com.joaorosa.myappcom.data.repository.UserProfileRepositoryImpl
import com.joaorosa.myappcom.data.repository.WorkoutRepositoryImpl
import com.joaorosa.myappcom.domain.repository.AuthRepository
import com.joaorosa.myappcom.domain.repository.NutritionRepository
import com.joaorosa.myappcom.domain.repository.UserProfileRepository
import com.joaorosa.myappcom.domain.repository.WorkoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        impl: UserProfileRepositoryImpl
    ): UserProfileRepository

    @Binds
    @Singleton
    abstract fun bindWorkoutRepository(
        impl: WorkoutRepositoryImpl
    ): WorkoutRepository

    @Binds
    @Singleton
    abstract fun bindNutritionRepository(
        impl: NutritionRepositoryImpl
    ): NutritionRepository
}
