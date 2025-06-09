package com.libro.librobackend.domain.readinggroup.service

import com.libro.librobackend.domain.readinggroup.entity.ReadingGroup
import com.libro.librobackend.domain.readinggroup.repository.ReadingGroupRepository
import org.springframework.stereotype.Service

@Service
class ReadingGroupService(
    val readingGroupRepository: ReadingGroupRepository
) {
    fun getReadingGroups(): List<ReadingGroup> = readingGroupRepository.findAll()
}