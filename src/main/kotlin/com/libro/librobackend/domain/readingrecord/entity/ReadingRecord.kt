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
    val bookTitle: String,
    val bookAuthor: String,
    val review: String?,
    val reviewDate: LocalDate?
) : BaseTimeEntity() {
    fun isOwnedBy(userId: Long): Boolean {
        return this.userId == userId
    }

    companion object {
        fun create(bookTitle: String, bookAuthor: String, userId: Long): ReadingRecord {
            return ReadingRecord(
                userId = userId,
                bookTitle = bookTitle,
                bookAuthor = bookAuthor,
                review = null,
                reviewDate = null
            )
        }
    }
}
