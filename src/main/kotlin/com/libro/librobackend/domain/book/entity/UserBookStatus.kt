package com.libro.librobackend.domain.book.entity

import com.libro.librobackend.domain.book.enums.UserBookStatusEnum
import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
class UserBookStatus(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: Long,

    val bookId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    val userBookStatusEnum: UserBookStatusEnum? = null,
) : BaseTimeEntity()


