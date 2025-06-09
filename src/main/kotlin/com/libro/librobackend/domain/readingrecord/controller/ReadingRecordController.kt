package com.libro.librobackend.domain.readingrecord.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.readingrecord.controller.rqrs.*
import com.libro.librobackend.domain.readingrecord.service.command.NoteCommandService
import com.libro.librobackend.domain.readingrecord.service.command.ReadingRecordCommandService
import com.libro.librobackend.domain.readingrecord.service.command.dto.NoteDeleteCommand
import com.libro.librobackend.domain.readingrecord.service.query.NoteQueryService
import com.libro.librobackend.domain.readingrecord.service.query.ReadingRecordQueryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reading-records")
class ReadingRecordController(
    private val readingRecordQueryService: ReadingRecordQueryService,
    private val readingRecordCommandService: ReadingRecordCommandService,
    private val noteQueryService: NoteQueryService,
    private val noteCommandService: NoteCommandService
) {

    @Operation(summary = "독서 레코드 목록 조회")
    @GetMapping
    fun getReadingRecords(@CurrentUserId userId: Long): ResponseEntity<List<ReadingRecordRs>> {
        val readingRecords = readingRecordQueryService.getReadingRecordsByUser(userId)
        return ResponseEntity.ok(readingRecords.map { ReadingRecordRs.from(it) })
    }

    @Operation(summary = "독서 레코드 정보 조회")
    @GetMapping("/{readingRecordId}")
    fun getReadingRecord(@PathVariable readingRecordId: Long): ResponseEntity<ReadingRecordRs> {
        val readingRecord = readingRecordQueryService.getReadingRecordById(readingRecordId)
        return ResponseEntity.ok(ReadingRecordRs.from(readingRecord))
    }

    @Operation(summary = "독서 레코드 등록")
    @PostMapping
    fun saveReadingRecord(@CurrentUserId userId: Long, @RequestBody rq: CreateReadingRecordRq): ResponseEntity<CreateReadingRecordRs> {
        val readingRecordId = readingRecordCommandService.saveReadingRecord(rq.toCommand(userId))
        return ResponseEntity.ok(CreateReadingRecordRs(readingRecordId))
    }

    @Operation(summary = "독서 레코드 삭제")
    @DeleteMapping("/{readingRecordId}")
    fun deleteReadingRecord(@PathVariable readingRecordId: Long): ResponseEntity<Void> {
        readingRecordCommandService.deleteReadingRecord(readingRecordId)
        return ResponseEntity.noContent().build()
    }

    @Operation(summary = "독서 레코드 > 기록 추가")
    @PostMapping("/{readingRecord}/note")
    fun saveNote(
        @CurrentUserId userId: Long,
        @PathVariable readingRecord: Long,
        @RequestBody rq: CreateNoteRq
    ): ResponseEntity<CreateNoteRs> {
        val noteId = noteCommandService.saveNote(rq.toCommand(userId, readingRecord))
        return ResponseEntity.ok(CreateNoteRs(noteId))
    }

    @Operation(summary = "독서 레코드 > 기록 조회")
    @GetMapping("/{readingRecordId}/notes")
    fun getNotes(@PathVariable readingRecordId: Long): ResponseEntity<List<NoteRs>> {
        val notes = noteQueryService.getNotesByReadingRecordId(readingRecordId)
        return ResponseEntity.ok(notes.map { NoteRs.from(it) })
    }

    @Operation(summary = "독서 레코드 > 기록 삭제")
    @DeleteMapping("/{readingRecordId}/notes/{noteId}")
    fun deleteNote(
        @CurrentUserId userId: Long,
        @PathVariable readingRecordId: Long,
        @PathVariable noteId: Long
    ): ResponseEntity<Void> {
        noteCommandService.delete(NoteDeleteCommand(userId, readingRecordId, noteId))
        return ResponseEntity.noContent().build()
    }

}