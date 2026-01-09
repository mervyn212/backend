package com.backenddemo.backend.graphql

import com.backenddemo.backend.entity.User
import com.backenddemo.backend.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller

@Controller
class UserQueryResolver(
    private val userService: UserService
) {

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    fun getUserById(@Argument id: Long): User? {
        return userService.getUserById(id)
    }
}
