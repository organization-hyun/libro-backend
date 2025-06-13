package com.libro.librobackend.domain.readingrecord.service.query.dto

data class ReadingRecordDto(
    val id: Long,
    val bookTitle: String,
    val bookAuthor: String
)