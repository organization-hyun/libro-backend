package com.libro.librobackend.domain.user.controller.rqrs

data class SignupRq(
    val email: String,
    val password: String
)