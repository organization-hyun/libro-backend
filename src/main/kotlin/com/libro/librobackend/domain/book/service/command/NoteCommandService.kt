package com.libro.librobackend.domain.book.service.command

import com.libro.librobackend.domain.book.entity.Note
import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.book.repository.NoteRepository
import com.libro.librobackend.domain.book.service.command.dto.CreateNoteCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NoteCommandService(
    private val bookRepository: BookRepository,
    private val noteRepository: NoteRepository
) {
    fun saveNote(command: CreateNoteCommand): Long {
        val userId = command.userId
        val bookId = command.bookId
        if (bookRepository.findById(bookId).orElseThrow().userId != userId) {
            throw IllegalArgumentException("권한이 없는 사용자입니다.")
        }

        val note = Note(
            bookId = bookId,
            content = command.content
        )
        return requireNotNull(noteRepository.save(note).id)
    }

}