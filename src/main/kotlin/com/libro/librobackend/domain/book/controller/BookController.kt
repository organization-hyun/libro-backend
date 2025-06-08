package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.book.controller.rqrs.*
import com.libro.librobackend.domain.book.service.command.BookCommandService
import com.libro.librobackend.domain.book.service.command.NoteCommandService
import com.libro.librobackend.domain.book.service.query.BookQueryService
import com.libro.librobackend.domain.book.service.query.NoteQueryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookQueryService: BookQueryService,
    private val bookCommandService: BookCommandService,
    private val noteQueryService: NoteQueryService,
    private val noteCommandService: NoteCommandService
) {

    @Operation(summary = "도서 목록 조회")
    @GetMapping
    fun getBooks(@CurrentUserId userId: Long): ResponseEntity<List<BookRs>> {
        val books = bookQueryService.getBooksByUser(userId)
        return ResponseEntity.ok(books.map { BookRs.from(it) })
    }

    @Operation(summary = "도서 조회")
    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: Long): ResponseEntity<BookRs> {
        val book = bookQueryService.getBookById(bookId)
        return ResponseEntity.ok(BookRs.from(book))
    }

    @Operation(summary = "도서 등록")
    @PostMapping
    fun saveBook(@CurrentUserId userId: Long, @RequestBody rq: CreateBookRq): ResponseEntity<CreateBookRs> {
        val bookId = bookCommandService.saveBook(rq.toCommand(userId))
        return ResponseEntity.ok(CreateBookRs(bookId))
    }

    @Operation(summary = "도서 삭제")
    @DeleteMapping("/{bookId}")
    fun deleteBook(@PathVariable bookId: Long): ResponseEntity<Void> {
        bookCommandService.deleteBook(bookId)
        return ResponseEntity.noContent().build()
    }

    @Operation(summary = "독서 기록 추가")
    @PostMapping("/{bookId}/note")
    fun saveNote(
        @CurrentUserId userId: Long,
        @PathVariable bookId: Long,
        @RequestBody rq: CreateNoteRq
    ): ResponseEntity<CreateNoteRs> {
        val noteId = noteCommandService.saveNote(rq.toCommand(userId, bookId))
        return ResponseEntity.ok(CreateNoteRs(noteId))
    }

    @Operation(summary = "독서 기록 조회")
    @GetMapping("/{bookId}/notes")
    fun getNotes(@PathVariable bookId: Long): ResponseEntity<List<NoteRs>> {
        val notes = noteQueryService.getNotesByBookId(bookId)
        return ResponseEntity.ok(notes.map { NoteRs.from(it) })
    }

}