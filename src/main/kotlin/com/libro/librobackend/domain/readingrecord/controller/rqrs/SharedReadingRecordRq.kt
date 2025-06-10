package com.libro.librobackend.domain.readingrecord.controller.rqrs

import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordCommand

data class SharedReadingRecordRq(
    val readingRecordId: Long
) {
    fun toCommand(userId: Long, readingGroupId: Long): SharedReadingRecordCommand {
        return SharedReadingRecordCommand(
            userId = userId,
            readingGroupId = readingGroupId,
            readingRecordId = readingRecordId
        )
    }
}