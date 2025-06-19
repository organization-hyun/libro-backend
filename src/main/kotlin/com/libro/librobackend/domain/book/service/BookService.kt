package com.libro.librobackend.domain.book.service

import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    fun searchBooks(q: String) : List<Book> {
        return bookRepository.findAllByTitleContains(q)
    }

}