package com.libro.librobackend.domain.readinggroup.service

import com.libro.librobackend.domain.readinggroup.entity.ReadingGroup
import com.libro.librobackend.domain.readinggroup.repository.ReadingGroupRepository
import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordCommand
import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordDto
import com.libro.librobackend.domain.readingrecord.repository.ReadingRecordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ReadingGroupService(
    val readingGroupRepository: ReadingGroupRepository,
    val readingRecordRepository: ReadingRecordRepository
) {
    fun getReadingGroups(): List<ReadingGroup> = readingGroupRepository.findAll()

    fun getReadingGroup(readingGroupId: Long): ReadingGroup =
        readingGroupRepository.findById(readingGroupId).orElseThrow()

    fun getSharedReadingRecords(readingGroupId: Long): List<SharedReadingRecordDto> {
        return readingGroupRepository.findSharedReadingRecords(readingGroupId)
    }

    @Transactional
    fun saveSharedReadingRecord(command: SharedReadingRecordCommand) {
        val readingRecord = readingRecordRepository.findById(command.readingRecordId).orElseThrow()
        if (!readingRecord.isOwnedBy(command.userId)) {
            throw IllegalArgumentException("사용자가 작성한 독서 기록이 아닙니다.")
        }
        val readingGroup = getReadingGroup(command.readingGroupId)
        readingGroup.addReadingRecord(requireNotNull(readingRecord.id))
    }

}