package com.libro.librobackend.domain.readingcompletion.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.readingcompletion.controller.rqrs.CreateReadingCompletionRq
import com.libro.librobackend.domain.readingcompletion.controller.rqrs.CreateReadingCompletionRs
import com.libro.librobackend.domain.readingcompletion.service.ReadingCompletionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

}