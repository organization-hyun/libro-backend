package com.libro.librobackend.domain.readingrecord.controller.rqrs

import com.libro.librobackend.domain.readingrecord.service.command.dto.CreateNoteCommand

data class CreateNoteRq(
    val content: String,
    val pageNumber: Int? = null,
) {
    fun toCommand(userId: Long, readingRecordId: Long): CreateNoteCommand {
        return CreateNoteCommand(
            userId = userId,
            readingRecordId = readingRecordId,
            content = content,
            pageNumber = pageNumber
        )
    }
}