package com.libro.librobackend.domain.book.controller.rqrs

data class CreateBookRq (
    val title: String,
    val author: String,
    val description: String,
)
