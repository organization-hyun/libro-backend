package com.libro.librobackend.domain.book.service.command

import com.libro.librobackend.domain.book.entity.Note
import com.libro.librobackend.domain.book.repository.BookRepository
import com.libro.librobackend.domain.book.repository.NoteRepository
import com.libro.librobackend.domain.book.service.command.dto.CreateNoteCommand
import com.libro.librobackend.domain.book.service.command.dto.NoteDeleteCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NoteCommandService(
    private val bookRepository: BookRepository,
    private val noteRepository: NoteRepository
) {
    fun saveNote(command: CreateNoteCommand): Long {
        val bookId = command.bookId
        val book = bookRepository.findById(bookId).orElseThrow()

        if (!book.isOwnedBy(command.userId)) {
            throw IllegalArgumentException("권한이 없는 사용자입니다.")
        }

        val note = Note(
            bookId = bookId,
            content = command.content,
            pageNumber = command.pageNumber
        )
        return requireNotNull(noteRepository.save(note).id)
    }

    fun delete(command: NoteDeleteCommand) {
        val note = noteRepository.findById(command.noteId).orElseThrow()
        val book = bookRepository.findById(note.bookId).orElseThrow()

        if (!book.isOwnedBy(command.userId)) {
            throw IllegalArgumentException("권한이 없는 사용자입니다.")
        }

        noteRepository.deleteById(command.noteId)
    }

}