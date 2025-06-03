package com.libro.librobackend.domain.book.service.command.dto

data class CreateBookCommand(
    val userId: Long,
    val title: String,
    val author: String
)