package com.libro.librobackend.domain.readingrecord.entity

import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class ReadingRecord(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    val bookId: Long,
    val bookTitle: String,
    val bookAuthor: String,
    val review: String?,
    val reviewDate: LocalDate?
) : BaseTimeEntity() {
    fun isOwnedBy(userId: Long): Boolean {
        return this.userId == userId
    }

    companion object {
        fun create(bookId: Long, userId: Long): ReadingRecord {
            return ReadingRecord(
                userId = userId,
                bookId = bookId,
                bookTitle = "",
                bookAuthor = "",
                review = null,
                reviewDate = null
            )
        }
    }
}
