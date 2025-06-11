package com.libro.librobackend.domain.user.controller.rqrs

data class SignupRq(
    val name: String,
    val email: String,
    val password: String
)