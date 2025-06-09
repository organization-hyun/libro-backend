package com.libro.librobackend.domain.readingrecord.service.query

import com.libro.librobackend.domain.readingrecord.entity.ReadingRecord
import com.libro.librobackend.domain.readingrecord.repository.ReadingRecordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ReadingRecordQueryService(
    private val readingRecordRepository: ReadingRecordRepository
) {

    fun getReadingRecordsByUser(userId: Long): List<ReadingRecord> =
        readingRecordRepository.findAllByUserId(userId)

    fun getReadingRecordById(readingRecordId: Long): ReadingRecord {
        return readingRecordRepository.findById(readingRecordId).orElseThrow()
    }

}