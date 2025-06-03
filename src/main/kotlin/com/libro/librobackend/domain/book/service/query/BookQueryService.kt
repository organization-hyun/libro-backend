package com.libro.librobackend.domain.book.service.query

import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.book.service.command.dto.CreateBookCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookQueryService(
    private val bookRepository: BookRepository
) {

    fun getBooksByUser(userId: Long): List<Book> =
        bookRepository.findAllByUserId(userId)

    fun getBookById(bookId: Long): Book {
        return bookRepository.findById(bookId).orElseThrow()
    }

}