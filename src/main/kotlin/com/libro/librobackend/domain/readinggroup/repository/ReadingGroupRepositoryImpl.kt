package com.libro.librobackend.domain.readinggroup.repository

import com.libro.librobackend.domain.readinggroup.entity.QSharedReadingRecord.sharedReadingRecord
import com.libro.librobackend.domain.readinggroup.service.dto.SharedReadingRecordDto
import com.libro.librobackend.domain.readingrecord.entity.QReadingRecord.readingRecord
import com.libro.librobackend.domain.user.entity.QUser.user
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory

class ReadingGroupRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ReadingGroupRepositoryCustom {
    override fun findSharedReadingRecords(readingGroupId: Long): List<SharedReadingRecordDto> {
        return jpaQueryFactory.select(
            Projections.constructor(
            SharedReadingRecordDto::class.java,
            sharedReadingRecord.readingRecordId,
                user.name,
                readingRecord.review,
                readingRecord.reviewDate
            ))
            .from(sharedReadingRecord)
            .join(readingRecord).on(sharedReadingRecord.readingRecordId.eq(readingRecord.id))
            .join(user).on(readingRecord.userId.eq(user.id))
            .where(sharedReadingRecord.readingGroup.id.eq(readingGroupId))
            .fetch()

    }
}