package com.libro.librobackend.domain.readingcompletion.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.readingcompletion.controller.rqrs.CreateReadingCompletionRq
import com.libro.librobackend.domain.readingcompletion.controller.rqrs.CreateReadingCompletionRs
import com.libro.librobackend.domain.readingcompletion.controller.rqrs.ReadingCompletionRs
import com.libro.librobackend.domain.readingcompletion.service.ReadingCompletionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reading-completions")
class ReadingCompletionController(
    private val readingCompletionService: ReadingCompletionService
) {

    @Operation(summary = "독서 완료 기록 생성")
    @PostMapping
    fun createReadingCompletion(
        @RequestBody rq: CreateReadingCompletionRq,
        @CurrentUserId userId: Long,
        ): ResponseEntity<CreateReadingCompletionRs> {
        val readingCompletionId = readingCompletionService.createReadingCompletion(rq, userId)
        return ResponseEntity.ok(CreateReadingCompletionRs.from(readingCompletionId))
    }

    @Operation(summary = "월별 독서 완료 기록 조회")
    @GetMapping
    fun getMonthlyReadingCompletions(
        @RequestParam("year") year: Int,
        @RequestParam("month") month: Int,
        @CurrentUserId userId: Long
    ): ResponseEntity<List<ReadingCompletionRs>> {
        val readingCompletions = readingCompletionService.getMonthlyReadingCompletions(userId, year, month)
        return ResponseEntity.ok(readingCompletions)
    }

}