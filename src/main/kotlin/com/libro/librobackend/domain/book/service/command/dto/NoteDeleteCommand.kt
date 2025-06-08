package com.libro.librobackend.domain.book.service.command.dto

class NoteDeleteCommand(
    val userId: Long,
    val bookId: Long,
    val noteId: Long
)