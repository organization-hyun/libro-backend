package com.libro.librobackend.domain.readingcompletion.controller.rqrs

data class ReadingCompletionRs(
    val id: Long,
    val date: String,
    val duration: Int,
    val bookId: Long?,
    val note: String?
) {
}