package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.domain.book.controller.rqrs.BookRs
import com.libro.librobackend.domain.book.controller.rqrs.CreateBookRq
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
    fun getBooks(@RequestParam userId: Long): ResponseEntity<List<BookRs>> {
        val books = bookService.getBooksByUser(userId)
        return ResponseEntity.ok(books.map { BookRs.from(it) })
    }

    @Operation(summary = "도서 조회")
    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: Long): ResponseEntity<BookRs> {
        val book = bookService.getBookById(bookId)
        return ResponseEntity.ok(BookRs.from(book))
    }

    @Operation(summary = "도서 등록")
    @PostMapping
    fun saveBook(@RequestBody rq: CreateBookRq): ResponseEntity<Long> {
        val bookId = bookService.saveBook(rq.toCommand())
        return ResponseEntity.ok(bookId)
    }

}