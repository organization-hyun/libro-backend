package com.libro.librobackend.domain.book.service.command

import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.book.service.command.dto.CreateBookCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookCommandService(
    private val bookRepository: BookRepository
) {

    fun saveBook(command: CreateBookCommand): Long? {
        val book = Book(
            title = command.title,
            author = command.author,
            userId = command.userId
        )
        return bookRepository.save(book).id
    }

    fun deleteBook(bookId: Long) {
        bookRepository.deleteById(bookId)
    }

}