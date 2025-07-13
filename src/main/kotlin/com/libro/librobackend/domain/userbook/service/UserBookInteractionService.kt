package com.libro.librobackend.domain.userbook.service

import com.libro.librobackend.domain.book.controller.UserBookStatusRs
import com.libro.librobackend.domain.book.controller.rqrs.CreateBookRq
import com.libro.librobackend.domain.book.entity.Book
import com.libro.librobackend.domain.book.entity.UserBookStatus
import com.libro.librobackend.domain.book.enums.UserBookStatusEnum
import com.libro.librobackend.domain.book.repository.UserBookStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class UserBookInteractionService(
    private val userBookStatusRepository: UserBookStatusRepository,
) {

    @Transactional
    fun wishBook(userId: Long, bookId: Long) {
        UserBookStatus(
            userId = userId,
            bookId = bookId,
            userBookStatusEnum = UserBookStatusEnum.WISH
        ).let { userBookStatus ->
            userBookStatusRepository.save(userBookStatus)
        }
    }

    fun searchUserBookStatus(userId: Long, bookId: Long): UserBookStatusRs {
        return UserBookStatusRs.from(userBookStatusRepository.findByUserIdAndBookId(userId, bookId))
    }
}