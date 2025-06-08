package com.libro.librobackend.domain.book.controller.rqrs

import com.libro.librobackend.domain.book.service.command.dto.CreateNoteCommand

data class CreateNoteRq(
    val content: String
) {
    fun toCommand(userId: Long, bookId: Long): CreateNoteCommand {
        return CreateNoteCommand(
            userId = userId,
            bookId = bookId,
            content = content
        )
    }
}