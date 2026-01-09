package com.backenddemo.backend.controller

import com.backenddemo.backend.entity.User
import com.backenddemo.backend.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/test")
class UserTestController(
    private val userService: UserService
) {

    // ✅ 1. Get all users (requires ADMIN)
    @GetMapping("/all")
    fun getAll(): List<User> = userService.getAllUsers()

    // ✅ 2. Get user by ID (requires ADMIN)
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): User = userService.getUserById(id)

    // ✅ 3. Debug: Get current JWT user info
    @GetMapping("/whoami")
    fun whoAmI(@AuthenticationPrincipal user: UserDetails): String {
        return "👤 Authenticated as: ${user.username}, Roles: ${user.authorities}"
    }
}
