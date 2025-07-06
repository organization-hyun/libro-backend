package com.libro.librobackend.domain.readingcompletion.controller.rqrs

import java.time.LocalDate

data class CreateReadingCompletionRq(
    val date: LocalDate,
    val duration: Int,
    val bookId: Long?,
    val note: String?
)
