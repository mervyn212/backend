package com.backenddemo.backend.controller

import com.backenddemo.backend.entity.User
import com.backenddemo.backend.repository.UserRepository
import com.backenddemo.backend.security.JwtUtil
import com.backenddemo.backend.security.MyUserDetailsService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userDetailsService: MyUserDetailsService,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Any> {
        return try {
            val authToken = UsernamePasswordAuthenticationToken(request.username, request.password)
            authManager.authenticate(authToken)

            val userDetails = userDetailsService.loadUserByUsername(request.username)
            val token = jwtUtil.generateToken(userDetails.username)

            ResponseEntity.ok(AuthResponse(token))
        } catch (ex: AuthenticationException) {
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<Any> {
        if (userRepository.findByUsername(request.username) != null) {
            return ResponseEntity.badRequest().body("User already exists")
        }

        val newUser = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            role = request.role
        )
        userRepository.save(newUser)
        val token = jwtUtil.generateToken(newUser.username)
        return ResponseEntity.ok(AuthResponse(token))
    }
}
