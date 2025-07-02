package com.libro.librobackend.domain.book.controller.rqrs

data class CreateBookRs(
    val id: Long
) {
    companion object {
        fun from(bookId: Long): CreateBookRs {
            return CreateBookRs(
                id = bookId
            )
        }
    }
}
