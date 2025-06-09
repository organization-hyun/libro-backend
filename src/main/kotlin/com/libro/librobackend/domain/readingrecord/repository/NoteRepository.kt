package com.libro.librobackend.domain.readingrecord.repository

import com.libro.librobackend.domain.readingrecord.entity.Note
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long> {
    fun findAllByReadingRecordId(readingRecordId: Long): List<Note>
}