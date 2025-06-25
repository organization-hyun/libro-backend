package com.libro.librobackend.domain.book.service

import com.libro.librobackend.domain.book.controller.UserBookStatusRs
import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.entity.UserBookStatus
import com.libro.librobackend.domain.book.enums.UserBookStatusEnum
import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.book.repository.UserBookStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookService(
    private val bookRepository: BookRepository,
    private val userBookStatusRepository: UserBookStatusRepository,
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

    @Transactional
    fun wishBook(userId: Long, bookId: Long) {
        UserBookStatus(
            userId = userId,
            bookId = bookId,
            userBookStatusEnum = UserBookStatusEnum.WISH
        ).let { userBookStatus ->
            userBookStatusRepository.save(userBookStatus)
        }
    }

    fun searchUserBookStatus(userId: Long, bookId: Long): UserBookStatusRs {
        return UserBookStatusRs.from(userBookStatusRepository.findByUserIdAndBookId(userId, bookId))
    }

}