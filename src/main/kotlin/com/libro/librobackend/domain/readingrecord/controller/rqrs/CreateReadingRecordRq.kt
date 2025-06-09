package com.libro.librobackend.domain.readingrecord.controller.rqrs

import com.libro.librobackend.domain.readingrecord.service.command.dto.CreateReadingRecordCommand

data class CreateReadingRecordRq(
    val bookTitle: String,
    val bookAuthor: String
) {
    fun toCommand(userId: Long): CreateReadingRecordCommand {
        return CreateReadingRecordCommand(
            userId = userId,
            bookTitle = bookTitle,
            bookAuthor = bookAuthor
        )
    }
}