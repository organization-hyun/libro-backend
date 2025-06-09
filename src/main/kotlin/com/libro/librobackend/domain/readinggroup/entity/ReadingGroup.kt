package com.libro.librobackend.domain.readinggroup.entity

import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ReadingGroup(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val bookTitle: String,
    val bookAuthor: String,
    val description: String
) : BaseTimeEntity()