package com.libro.librobackend.domain.readinggroup.service

import com.libro.librobackend.domain.readinggroup.entity.ReadingGroup
import com.libro.librobackend.domain.readinggroup.repository.ReadingGroupRepository
import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordDto
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReadingGroupService(
    val readingGroupRepository: ReadingGroupRepository
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

}