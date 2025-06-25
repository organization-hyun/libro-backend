package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.domain.book.service.BookService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {

    @Operation(summary = "도서 목록 조회")
    @GetMapping
    fun getBooks(): List<BookRs> =
        bookService.getBooks().map { BookRs.from(it) }

    @Operation(summary = "도서 검색")
    @GetMapping("/search")
    fun searchBooks(@RequestParam q: String): ResponseEntity<List<BookRs>> {
        val books = bookService.searchBooks(q)
        return ResponseEntity.ok(books.map { BookRs.from(it) })
    }

    @Operation(summary = "도서 정보 조회")
    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: Long): ResponseEntity<BookRs> {
        val book = bookService.getBook(bookId)
        return ResponseEntity.ok(BookRs.from(book))
    }

}