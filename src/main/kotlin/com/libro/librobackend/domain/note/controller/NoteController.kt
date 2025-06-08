package com.libro.librobackend.domain.note.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.book.service.command.NoteCommandService
import com.libro.librobackend.domain.book.service.command.dto.NoteDeleteCommand
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val noteCommandService: NoteCommandService
) {

    @Operation(summary = "기록 삭제")
    @DeleteMapping("/{noteId}")
    fun deleteNote(
        @CurrentUserId userId: Long,
        @PathVariable noteId: Long
    ): ResponseEntity<Void> {
        noteCommandService.delete(NoteDeleteCommand(userId, noteId))
        return ResponseEntity.noContent().build()
    }

}