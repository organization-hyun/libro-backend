package com.libro.librobackend.domain.user.service

import com.libro.librobackend.domain.user.entity.User
import com.libro.librobackend.domain.user.repository.UserRepository
import com.libro.librobackend.global.util.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
) {
    fun signup(name: String, email: String, password: String): User {
        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("이미 존재하는 이메일입니다.")
        }
        val encodedPassword = passwordEncoder.encode(password)
        val user = User(name = name, email = email, password = encodedPassword)
        return userRepository.save(user)
    }

    fun login(email: String, password: String): String {
        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("존재하지 않는 사용자입니다.")

        if (!passwordEncoder.matches(password, user.password)) {
            throw IllegalArgumentException("비밀번호가 틀렸습니다.")
        }

        return jwtUtil.generateToken(requireNotNull(user.id))
    }
}