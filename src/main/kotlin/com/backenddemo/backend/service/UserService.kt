package com.backenddemo.backend.service

import com.backenddemo.backend.entity.User
import com.backenddemo.backend.repository.UserRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Cacheable(value = ["users"], key = "#id")
    fun getUserById(id: Long): User {
        println("🔁 Fetching from DB for ID: $id")
        return userRepository.findById(id).orElseThrow { NoSuchElementException("User not found") }
    }

    fun getAllUsers(): List<User> =
        userRepository.findAll()

    fun createUser(user: User): User =
        userRepository.save(user)
}
