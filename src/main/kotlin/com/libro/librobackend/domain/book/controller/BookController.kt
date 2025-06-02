package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.domain.book.controller.rqrs.BookRs
import com.libro.librobackend.domain.book.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {

    @GetMapping
    fun getBooks(@RequestParam userId: Long): ResponseEntity<List<BookRs>> {
        val books = bookService.getBooksByUser(userId)
        return ResponseEntity.ok(books.map { BookRs.from(it) })
    }

    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: Long): ResponseEntity<BookRs> {
        val book = bookService.getBookById(bookId)
        return ResponseEntity.ok(BookRs.from(book))
    }

}