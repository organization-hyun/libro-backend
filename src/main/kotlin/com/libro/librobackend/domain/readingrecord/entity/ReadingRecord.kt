package com.libro.librobackend.domain.readingrecord.entity

import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ReadingRecord(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    val bookTitle: String,
    val bookAuthor: String,
) : BaseTimeEntity() {
    fun isOwnedBy(userId: Long): Boolean {
        return this.userId == userId
    }
}
