package com.libro.librobackend.domain.book.repository

import com.libro.librobackend.domain.book.entity.Note
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long>