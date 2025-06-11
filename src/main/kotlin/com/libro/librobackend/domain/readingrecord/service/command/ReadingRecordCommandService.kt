package com.libro.librobackend.domain.readingrecord.service.command

import com.libro.librobackend.domain.readingrecord.entity.ReadingRecord
import com.libro.librobackend.domain.readingrecord.repository.ReadingRecordRepository
import com.libro.librobackend.domain.readingrecord.service.command.dto.CreateReadingRecordCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ReadingRecordCommandService(
    private val readingRecordRepository: ReadingRecordRepository
) {

    fun saveReadingRecord(command: CreateReadingRecordCommand): Long {
        val readingRecord = ReadingRecord.create(
            bookTitle = command.bookTitle,
            bookAuthor = command.bookAuthor,
            userId = command.userId
        )
        return requireNotNull(readingRecordRepository.save(readingRecord).id)
    }

    fun deleteReadingRecord(readingRecordId: Long) {
        readingRecordRepository.deleteById(readingRecordId)
    }

}