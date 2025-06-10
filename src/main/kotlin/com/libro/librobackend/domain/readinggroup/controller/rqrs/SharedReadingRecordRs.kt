package com.libro.librobackend.domain.readinggroup.controller.rqrs

import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordDto
import java.time.LocalDate

data class SharedReadingRecordRs(
    val readingRecordId: Long,
    val writerName: String,
    val review: String,
    val sharedDate: LocalDate
) {
    companion object {
        fun from(record: SharedReadingRecordDto): SharedReadingRecordRs {
            return SharedReadingRecordRs(
                readingRecordId = record.readingRecordId,
                writerName = record.writerName,
                review = record.review,
                sharedDate = record.sharedDate
            )
        }
    }
}