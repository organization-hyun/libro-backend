package com.libro.librobackend.domain.userbook.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.book.controller.UserBookStatusRs
import com.libro.librobackend.domain.userbook.service.UserBookInteractionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/user-book/interaction")
class UserBookInteractionController(
    private val userBookInteractionService: UserBookInteractionService
) {

    @Operation(summary = "도서별 사용자의 읽고싶어요 등록")
    @PostMapping("/{bookId}/wish")
    fun wishBook(
        @CurrentUserId userId: Long,
        @PathVariable bookId: Long
    ): ResponseEntity<Void> {
        userBookInteractionService.wishBook(userId, bookId)
        return ResponseEntity.noContent().build()
    }

    @Operation(summary = "특정 도서의 현재 사용자가 갖고 있는 상태 조회")
    @GetMapping("/{bookId}/status")
    fun searchUserBookStatus(
        @CurrentUserId userId: Long,
        @PathVariable bookId: Long
    ): ResponseEntity<UserBookStatusRs> {
        val searchUserBookStatus = userBookInteractionService.searchUserBookStatus(userId, bookId)
        return ResponseEntity.ok(searchUserBookStatus)
    }

}