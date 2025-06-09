package com.libro.librobackend.domain.readinggroup.controller.rqrs

import com.libro.librobackend.domain.readinggroup.entity.ReadingGroup

data class ReadingGroupRs(
    val id: Long,
    val bookTitle: String,
    val bookAuthor: String,
    val description: String
) {
    companion object {
        fun from(it: ReadingGroup): ReadingGroupRs {
            return ReadingGroupRs(
                id = requireNotNull(it.id),
                bookTitle = it.bookTitle,
                bookAuthor = it.bookAuthor,
                description = it.description
            )
        }
    }
}