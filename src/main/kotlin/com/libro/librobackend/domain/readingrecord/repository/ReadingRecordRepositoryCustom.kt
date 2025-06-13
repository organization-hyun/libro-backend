package com.libro.librobackend.domain.readingrecord.repository

import com.libro.librobackend.domain.readingrecord.service.query.dto.ReadingRecordDto

interface ReadingRecordRepositoryCustom {
    fun findAllWithBookInfoByUserId(userId: Long): List<ReadingRecordDto>
}