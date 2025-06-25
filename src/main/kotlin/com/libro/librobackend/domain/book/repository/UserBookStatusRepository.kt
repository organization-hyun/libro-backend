package com.libro.librobackend.domain.book.repository

import com.libro.librobackend.domain.book.entity.UserBookStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserBookStatusRepository : JpaRepository<UserBookStatus, Long> {
    fun findByUserIdAndBookId(userId: Long, bookId: Long): List<UserBookStatus>
}