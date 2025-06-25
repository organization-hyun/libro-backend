package com.libro.librobackend.domain.book.service

import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookService(
    private val bookRepository: BookRepository
) {

    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    fun searchBooks(q: String) : List<Book> {
        return bookRepository.findAllByTitleContains(q)
    }

    fun getBook(bookId: Long): Book {
        return bookRepository.findById(bookId).orElseThrow()
    }

}