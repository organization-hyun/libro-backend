package com.libro.librobackend.domain.readingrecord.service.query

import com.libro.librobackend.domain.readingrecord.entity.Note
import com.libro.librobackend.domain.readingrecord.repository.NoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NoteQueryService(
    private val noteRepository: NoteRepository
) {
    fun getNotesByReadingRecordId(readingRecordId: Long): List<Note> {
        return noteRepository.findAllByReadingRecordId(readingRecordId)
    }


}