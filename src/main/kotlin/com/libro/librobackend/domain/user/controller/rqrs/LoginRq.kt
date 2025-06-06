package com.libro.librobackend.domain.user.controller.rqrs

data class LoginRq(
    val email: String,
    val password: String
)