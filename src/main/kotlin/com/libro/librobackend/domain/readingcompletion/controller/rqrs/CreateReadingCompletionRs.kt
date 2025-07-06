package com.libro.librobackend.domain.readingcompletion.controller.rqrs

data class CreateReadingCompletionRs(
    val id: Long
) {
    companion object {
        fun from(id: Long): CreateReadingCompletionRs {
            return CreateReadingCompletionRs(id = id)
        }
    }
}
