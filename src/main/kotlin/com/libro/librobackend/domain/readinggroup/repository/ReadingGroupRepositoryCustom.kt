package com.libro.librobackend.domain.readinggroup.repository

import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordDto

interface ReadingGroupRepositoryCustom {
    fun findSharedReadingRecords(readingGroupId: Long) : List<SharedReadingRecordDto>
}