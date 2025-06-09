package com.libro.librobackend.domain.readinggroup.controller.rqrs

import com.libro.librobackend.domain.readinggroup.entity.ReadingGroup

data class ReadingGroupDetailRs(
    val bookTitle: String,
    val bookAuthor: String,
    val description: String
) {
    companion object {
        fun from(it: ReadingGroup): ReadingGroupDetailRs {
            return ReadingGroupDetailRs(
                bookTitle = it.bookTitle,
                bookAuthor = it.bookAuthor,
                description = it.description
            )
        }
    }
}