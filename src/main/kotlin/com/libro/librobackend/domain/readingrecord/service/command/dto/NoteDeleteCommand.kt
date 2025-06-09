package com.libro.librobackend.domain.readingrecord.service.command.dto

class NoteDeleteCommand(
    val userId: Long,
    val readingRecordId: Long,
    val noteId: Long
)