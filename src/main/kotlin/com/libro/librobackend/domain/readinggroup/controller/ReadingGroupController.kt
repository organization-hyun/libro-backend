package com.libro.librobackend.domain.readinggroup.controller

import com.libro.librobackend.domain.readinggroup.controller.rqrs.ReadingGroupRs
import com.libro.librobackend.domain.readinggroup.service.ReadingGroupService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reading-groups")
class ReadingGroupController(
    private val readingGroupService: ReadingGroupService,
) {

    @Operation(summary = "독서 모임 목록 조회")
    @GetMapping
    fun getReadingGroups(): ResponseEntity<List<ReadingGroupRs>> {
        val readingGroups = readingGroupService.getReadingGroups()
        return ResponseEntity.ok(readingGroups.map { ReadingGroupRs.from(it) })
    }

}