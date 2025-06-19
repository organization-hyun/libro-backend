package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.book.service.BookService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {

    @Operation(summary = "도서 목록 조회")
    @GetMapping
    fun getBooks(): List<BookRs> =
        bookService.getBooks().map { BookRs.from(it) }

    @Operation(summary = "도서별 사용자의 읽고싶어요 등록")
    @PostMapping("/{bookId}/wish")
    fun wishBook(
        @CurrentUserId userId: Long,
        @PathVariable bookId: Long
    ): ResponseEntity<Void> {
        bookService.wishBook(userId, bookId)
        return ResponseEntity.noContent().build()
    }




}