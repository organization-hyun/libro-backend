package com.libro.librobackend.domain.book.entity

import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long,
    val title: String,
    val author: String,
) : BaseTimeEntity() {
    fun isOwnedBy(userId: Long): Boolean {
        return this.userId == userId
    }
}
