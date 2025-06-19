package com.libro.librobackend.domain.book.repository

import com.libro.librobackend.domain.book.entity.UserBookStatus
import com.libro.librobackend.domain.book.enums.UserBookStatusEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UserBookStatusRepository : JpaRepository<UserBookStatus, Long> {

    @Modifying
    @Query("""INSERT INTO user_book_status (user_id, book_id, status) VALUES (:userId, :bookId, :status)
           ON CONFLICT (user_id, book_id) DO UPDATE SET status = :status""", nativeQuery = true)
    // todo - Native Query  제외하고 JPA로 변경하되 연관관계를 굳이 맺을 필요 없음
    fun insertWishBook(userId: Long, bookId: Long, status: UserBookStatusEnum)

}