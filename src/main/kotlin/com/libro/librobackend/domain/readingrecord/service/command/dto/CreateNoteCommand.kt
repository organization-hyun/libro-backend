package com.libro.librobackend.domain.readingrecord.service.command.dto

data class CreateNoteCommand(
    val userId: Long,
    val readingRecordId: Long,
    val content: String,
    val pageNumber: Int? = null
)