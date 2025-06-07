package com.libro.librobackend.domain.book.entity

import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Note(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val bookId: Long,
    val content: String,
) : BaseTimeEntity()