package com.libro.librobackend.domain.readingcompletion.repository

import com.libro.librobackend.domain.readingcompletion.entity.ReadingCompletion
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ReadingCompletionRepository : JpaRepository<ReadingCompletion, Long> {
    fun findByUserIdAndDateBetween(userId: Long, startDate: LocalDate, endDate: LocalDate): List<ReadingCompletion>
}
