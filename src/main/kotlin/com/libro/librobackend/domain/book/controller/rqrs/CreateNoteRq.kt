package com.libro.librobackend.domain.book.controller.rqrs

import com.libro.librobackend.domain.book.service.command.dto.CreateNoteCommand

data class CreateNoteRq(
    val content: String
) {
    fun toCommand(bookId: Long): CreateNoteCommand {
        return CreateNoteCommand(
            bookId = bookId,
            content = content
        )
    }
}