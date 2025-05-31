package com.libro.librobackend.domain.book.repository

import com.libro.librobackend.domain.book.entity.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    fun findAllByUserId(userId: Long): List<Book>
}