package com.libro.librobackend.domain.readingrecord.service.query

import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.readingrecord.repository.ReadingRecordRepository
import com.libro.librobackend.domain.readingrecord.service.query.dto.ReadingRecordDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ReadingRecordQueryService(
    private val readingRecordRepository: ReadingRecordRepository,
    private val bookRepository: BookRepository
) {

    fun getReadingRecordsByUser(userId: Long): List<ReadingRecordDto> = readingRecordRepository.findAllWithBookInfoByUserId(userId)

    fun getReadingRecordById(readingRecordId: Long): ReadingRecordDto {
        val readingRecord = readingRecordRepository.findById(readingRecordId).orElseThrow()
        val book = bookRepository.findById(readingRecord.bookId).orElseThrow()
        return ReadingRecordDto(
            id = readingRecordId,
            bookTitle = book.title,
            bookAuthor = book.author
        )
    }

}