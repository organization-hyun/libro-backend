package com.libro.librobackend.domain.readingcompletion.service

import com.libro.librobackend.domain.readingcompletion.controller.rqrs.CreateReadingCompletionRq
import com.libro.librobackend.domain.readingcompletion.entity.ReadingCompletion
import com.libro.librobackend.domain.readingcompletion.repository.ReadingCompletionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ReadingCompletionService(
    private val readingCompletionRepository: ReadingCompletionRepository
) {

    @Transactional
    fun createReadingCompletion(rq: CreateReadingCompletionRq, userId: Long): Long {
        val readingCompletion = ReadingCompletion(
            date = rq.date,
            duration = rq.duration,
            bookId = rq.bookId,
            userId = userId,
            note = rq.note
        )
        return readingCompletionRepository.save(readingCompletion).id!!
    }
}
