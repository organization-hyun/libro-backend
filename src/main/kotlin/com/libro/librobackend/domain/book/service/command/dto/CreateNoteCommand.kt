package com.libro.librobackend.domain.book.service.command.dto

data class CreateNoteCommand(
    val userId: Long,
    val bookId: Long,
    val content: String,
    val pageNumber: Int? = null
)