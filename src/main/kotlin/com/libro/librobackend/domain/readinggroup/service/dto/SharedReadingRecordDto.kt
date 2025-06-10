package com.libro.librobackend.domain.readinggroup.service.dto

import java.time.LocalDate

data class SharedReadingRecordDto(
    val readingRecordId: Long,
    val writerName: String,
    val review: String,
    val sharedDate: LocalDate
)