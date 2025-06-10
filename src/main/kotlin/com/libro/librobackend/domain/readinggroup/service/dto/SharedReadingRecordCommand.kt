package com.libro.librobackend.domain.readinggroup.service.dto

data class SharedReadingRecordCommand(
    val userId: Long,
    val readingGroupId: Long,
    val readingRecordId: Long
)