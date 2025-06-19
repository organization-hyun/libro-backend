package com.libro.librobackend.domain.book.service

import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.enums.UserBookStatusEnum
import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.book.repository.UserBookStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val userBookStatusRepository: UserBookStatusRepository,
) {
    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    @Transactional
    fun wishBook(userId: Long, bookId: Long) {

        userBookStatusRepository.insertWishBook(
            userId = userId,
            bookId = bookId,
            status = UserBookStatusEnum.WISH
        )
    }
}