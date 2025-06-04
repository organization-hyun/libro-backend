package com.libro.librobackend.domain.book.service.command.dto

data class CreateNoteCommand(
    val bookId: Long,
    val content: String
)