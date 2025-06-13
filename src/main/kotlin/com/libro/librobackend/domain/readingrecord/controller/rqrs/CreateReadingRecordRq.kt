package com.libro.librobackend.domain.readingrecord.controller.rqrs

import com.libro.librobackend.domain.readingrecord.service.command.dto.CreateReadingRecordCommand

data class CreateReadingRecordRq(
    val bookId: Long
) {
    fun toCommand(userId: Long): CreateReadingRecordCommand {
        return CreateReadingRecordCommand(
            userId = userId,
            bookId = bookId
        )
    }
}