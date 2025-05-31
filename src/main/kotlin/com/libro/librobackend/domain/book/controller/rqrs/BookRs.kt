package com.libro.librobackend.domain.book.controller.rqrs

import com.libro.librobackend.domain.book.entity.Book

data class BookRs(
    val id: Long,
    val title: String,
    val author: String
) {
    companion object {
        fun from(book: Book): BookRs {
            return BookRs(
                id = book.id,
                title = book.title,
                author = book.author
            )
        }
    }
}