package com.mystacademy.android.modules.user_profile

import com.mystacademy.android.repositories.user.UserRepository

data class UserProfileDependencies(
    val userRepository: UserRepository
)