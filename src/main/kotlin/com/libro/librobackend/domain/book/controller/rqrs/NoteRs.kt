package com.libro.librobackend.domain.book.controller.rqrs

import com.libro.librobackend.domain.book.entity.Note

data class NoteRs(
    val id: Long,
    val content: String,
) {
    companion object {
        fun from(note: Note): NoteRs {
            val noteId = requireNotNull(note.id)
            return NoteRs(
                id = noteId,
                content = note.content
            )
        }
    }
}