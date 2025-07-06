package com.libro.librobackend.domain.readingcompletion.service

import com.libro.librobackend.domain.readingcompletion.controller.rqrs.CreateReadingCompletionRq
import com.libro.librobackend.domain.readingcompletion.controller.rqrs.ReadingCompletionRs
import com.libro.librobackend.domain.readingcompletion.entity.ReadingCompletion
import com.libro.librobackend.domain.readingcompletion.repository.ReadingCompletionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.YearMonth

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

    fun getMonthlyReadingCompletions(userId: Long, year: Int, month: Int): List<ReadingCompletionRs> {
        val startDate = LocalDate.of(year, month, 1)
        val endDate = YearMonth.of(year, month).atEndOfMonth()
        val readingCompletions = readingCompletionRepository.findByUserIdAndDateBetween(userId, startDate, endDate)

        return readingCompletions.map {
            ReadingCompletionRs(
                id = it.id!!,
                date = it.date.toString(),
                duration = it.duration,
                bookId = it.bookId,
                note = it.note
            )
        }
    }
}
