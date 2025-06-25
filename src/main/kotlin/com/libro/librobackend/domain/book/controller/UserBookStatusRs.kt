package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.domain.book.entity.UserBookStatus
import com.libro.librobackend.domain.book.enums.UserBookStatusEnum

data class UserBookStatusRs(
    val status: List<UserBookStatusEnum>
) {
    companion object {
        fun from(userBookStatuses: List<UserBookStatus>): UserBookStatusRs {
            val enums = userBookStatuses.mapNotNull { it.userBookStatusEnum }
                .distinct()
            return UserBookStatusRs(
                status = enums
            )
        }
    }
}