package com.libro.librobackend.domain.readingrecord.controller.rqrs

import com.libro.librobackend.domain.readingrecord.service.query.dto.ReadingRecordDto

data class ReadingRecordRs(
    val id: Long,
    val bookTitle: String,
    val bookAuthor: String
) {
    companion object {
        fun from(readingRecord: ReadingRecordDto): ReadingRecordRs {
            val readingRecordId = requireNotNull(readingRecord.id)
            return ReadingRecordRs(
                id = readingRecordId,
                bookTitle = readingRecord.bookTitle,
                bookAuthor = readingRecord.bookAuthor
            )
        }
    }
}