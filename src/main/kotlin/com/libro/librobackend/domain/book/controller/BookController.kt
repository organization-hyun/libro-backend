package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.domain.book.controller.rqrs.BookRs
import com.libro.librobackend.domain.book.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {

    @GetMapping
    fun getBooks(@RequestParam userId: Long): ResponseEntity<List<BookRs>> {
        val books = bookService.getBooksByUser(userId)
        return ResponseEntity.ok(books)
    }

}