package com.libro.librobackend.domain.book.entity

import com.libro.librobackend.domain.book.enums.UserBookStatusEnum
import com.libro.librobackend.domain.common.BaseTimeEntity
import com.libro.librobackend.domain.user.entity.User
import jakarta.persistence.*

@Entity
class UserBookStatus(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User, // todo - 삭제

    // todo - Native Query  제외하고 JPA로 변경하되 연관관계를 굳이 맺을 필요 없음
    val userId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    val book: Book, // todo - 삭제

    // todo - Native Query  제외하고 JPA로 변경하되 연관관계를 굳이 맺을 필요 없음
    val bookId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    val userBookStatusEnum: UserBookStatusEnum? = null,
) : BaseTimeEntity()


