package com.libro.librobackend.domain.book.controller

import com.libro.librobackend.domain.book.service.BookService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
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

}