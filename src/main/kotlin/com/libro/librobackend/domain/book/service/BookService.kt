package com.libro.librobackend.domain.book.service

import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.book.service.dto.CreateBookCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService(
    private val bookRepository: BookRepository
) {

    @Transactional(readOnly = true)
    fun getBooksByUser(userId: Long): List<Book> =
        bookRepository.findAllByUserId(userId)

    fun getBookById(bookId: Long): Book {
        return bookRepository.findById(bookId).orElseThrow()
    }

    fun saveBook(command: CreateBookCommand): Long? {
        val book = Book(
            title = command.title,
            author = command.author,
            userId = command.userId
        )
        return bookRepository.save(book).id
    }

}