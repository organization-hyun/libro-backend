package com.libro.librobackend.domain.book.controller.rqrs

import com.libro.librobackend.domain.book.entity.Book

data class BookRs(
    val id: Long,
    val title: String,
    val author: String
) {
    companion object {
        fun from(book: Book): BookRs {
            val bookId = requireNotNull(book.id)
            return BookRs(
                id = bookId,
                title = book.title,
                author = book.author
            )
        }
    }
}