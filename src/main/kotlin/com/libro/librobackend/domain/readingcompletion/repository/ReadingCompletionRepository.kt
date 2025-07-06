package com.libro.librobackend.domain.readingcompletion.repository

import com.libro.librobackend.domain.readingcompletion.entity.ReadingCompletion
import org.springframework.data.jpa.repository.JpaRepository

interface ReadingCompletionRepository : JpaRepository<ReadingCompletion, Long> {
}
