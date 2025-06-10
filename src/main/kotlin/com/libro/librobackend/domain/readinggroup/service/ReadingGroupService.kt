package com.libro.librobackend.domain.readinggroup.service

import com.libro.librobackend.domain.readinggroup.entity.ReadingGroup
import com.libro.librobackend.domain.readinggroup.repository.ReadingGroupRepository
import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordCommand
import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordDto
import com.libro.librobackend.domain.readingrecord.repository.ReadingRecordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

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
        // 더미 데이터 응답
        // TODO 로직 구현
        return listOf(
            SharedReadingRecordDto(
                readingRecordId = 1L,
                writerName = "홍길동",
                review = "이 책 정말 좋았어요!",
                sharedDate = LocalDate.of(2025, 6, 1)
            ),
            SharedReadingRecordDto(
                readingRecordId = 2L,
                writerName = "김철수",
                review = "생각보다 지루했어요.",
                sharedDate = LocalDate.of(2025, 6, 10)
            )
        )
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