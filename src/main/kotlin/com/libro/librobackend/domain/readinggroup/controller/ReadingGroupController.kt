package com.libro.librobackend.domain.readinggroup.controller

import com.libro.librobackend.config.security.annotation.CurrentUserId
import com.libro.librobackend.domain.readinggroup.controller.rqrs.ReadingGroupDetailRs
import com.libro.librobackend.domain.readinggroup.controller.rqrs.ReadingGroupRs
import com.libro.librobackend.domain.readinggroup.controller.rqrs.SharedReadingRecordRs
import com.libro.librobackend.domain.readinggroup.service.ReadingGroupService
import com.libro.librobackend.domain.readingrecord.controller.rqrs.SharedReadingRecordRq
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @Operation(summary = "독서 모임 조회")
    @GetMapping("/{readingGroupId}")
    fun getReadingGroup(@PathVariable readingGroupId: Long): ResponseEntity<ReadingGroupDetailRs> {
        val readingGroup = readingGroupService.getReadingGroup(readingGroupId)
        return ResponseEntity.ok(ReadingGroupDetailRs.from(readingGroup))
    }

    @Operation(summary = "공유된 독서기록 조회")
    @GetMapping("/{readingGroupId}/shared-reading-records")
    fun getSharedReadingRecords(@PathVariable readingGroupId: Long): ResponseEntity<List<SharedReadingRecordRs>> {
        val records = readingGroupService.getSharedReadingRecords(readingGroupId)
        return ResponseEntity.ok(records.map { SharedReadingRecordRs.from(it) })
    }

    @Operation(summary = "독서기록 공유")
    @PostMapping("/{readingGroupId}/shared-reading-record")
    fun saveSharedReadingRecord(
        @CurrentUserId userId: Long,
        @PathVariable readingGroupId: Long,
        @RequestBody rq: SharedReadingRecordRq
    ): ResponseEntity<Void> {
        readingGroupService.saveSharedReadingRecord(rq.toCommand(userId, readingGroupId))
        return ResponseEntity.noContent().build()
    }

}