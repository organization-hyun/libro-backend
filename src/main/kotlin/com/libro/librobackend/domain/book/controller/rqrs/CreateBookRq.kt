package com.libro.librobackend.domain.book.controller.rqrs

import com.libro.librobackend.domain.book.service.command.dto.CreateBookCommand

data class CreateBookRq(
    val userId: Long,
    val title: String,
    val author: String
) {
    fun toCommand(): CreateBookCommand {
        return CreateBookCommand(
            userId = userId,
            title = title,
            author = author
        )
    }
}