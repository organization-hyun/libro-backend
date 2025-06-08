package com.libro.librobackend.domain.book.controller.rqrs

import com.libro.librobackend.domain.book.entity.Note

data class NoteRs(
    val id: Long,
    val content: String,
    val pageNumber: Int?
) {
    companion object {
        fun from(note: Note): NoteRs {
            val noteId = requireNotNull(note.id)
            return NoteRs(
                id = noteId,
                content = note.content,
                pageNumber = note.pageNumber
            )
        }
    }
}