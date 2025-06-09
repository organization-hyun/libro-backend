package com.libro.librobackend.domain.readingrecord.service.command

import com.libro.librobackend.domain.readingrecord.entity.Note
import com.libro.librobackend.domain.readingrecord.repository.NoteRepository
import com.libro.librobackend.domain.readingrecord.repository.ReadingRecordRepository
import com.libro.librobackend.domain.readingrecord.service.command.dto.CreateNoteCommand
import com.libro.librobackend.domain.readingrecord.service.command.dto.NoteDeleteCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NoteCommandService(
    private val readingRecordRepository: ReadingRecordRepository,
    private val noteRepository: NoteRepository
) {
    fun saveNote(command: CreateNoteCommand): Long {
        val readingRecordId = command.readingRecordId
        val readingRecord = readingRecordRepository.findById(readingRecordId).orElseThrow()

        if (!readingRecord.isOwnedBy(command.userId)) {
            throw IllegalArgumentException("권한이 없는 사용자입니다.")
        }

        val note = Note(
            readingRecordId = readingRecordId,
            content = command.content,
            pageNumber = command.pageNumber
        )
        return requireNotNull(noteRepository.save(note).id)
    }

    fun delete(command: NoteDeleteCommand) {
        val readingRecord = readingRecordRepository.findById(command.readingRecordId).orElseThrow()

        if (!readingRecord.isOwnedBy(command.userId)) {
            throw IllegalArgumentException("권한이 없는 사용자입니다.")
        }

        noteRepository.deleteById(command.noteId)
    }

}