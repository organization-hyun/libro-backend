package com.libro.librobackend.domain.book.service.command

import com.libro.librobackend.domain.book.entity.Note
import com.libro.librobackend.domain.book.repository.NoteRepository
import com.libro.librobackend.domain.book.service.command.dto.CreateNoteCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NoteCommandService(
    private val noteRepository: NoteRepository
) {
    fun saveNote(command: CreateNoteCommand): Long? {
        val note = Note(
            bookId = command.bookId,
            content = command.content
        )
        return noteRepository.save(note).id
    }

}