package com.libro.librobackend.domain.readingrecord.service.command.dto

data class CreateReadingRecordCommand(
    val userId: Long,
    val bookId: Long
)