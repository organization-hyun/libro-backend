package com.libro.librobackend.domain.user.controller

import com.libro.librobackend.domain.user.controller.rqrs.LoginRq
import com.libro.librobackend.domain.user.controller.rqrs.LoginRs
import com.libro.librobackend.domain.user.controller.rqrs.SignupRq
import com.libro.librobackend.domain.user.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @Operation(summary = "회원 가입")
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRq) = authService.signup(request.email, request.password)

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRq): ResponseEntity<LoginRs> {
        val token = authService.login(request.email, request.password)
        return ResponseEntity.ok(LoginRs(token))
    }

}