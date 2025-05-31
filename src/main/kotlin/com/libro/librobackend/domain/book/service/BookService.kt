package com.libro.librobackend.domain.book.service

import com.libro.librobackend.domain.book.controller.rqrs.BookRs
import com.libro.librobackend.domain.book.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService(
    private val bookRepository: BookRepository
) {

    @Transactional(readOnly = true)
    fun getBooksByUser(userId: Long): List<BookRs> =
        bookRepository.findAllByUserId(userId).map { BookRs.from(it) }

}