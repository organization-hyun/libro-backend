package com.libro.librobackend.domain.book.service.query

import com.libro.librobackend.domain.book.entity.Note
import com.libro.librobackend.domain.book.repository.NoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NoteQueryService(
    private val noteRepository: NoteRepository
) {
    fun getNotesByBookId(bookId: Long): List<Note> {
        return noteRepository.findAllByBookId(bookId)
    }


}