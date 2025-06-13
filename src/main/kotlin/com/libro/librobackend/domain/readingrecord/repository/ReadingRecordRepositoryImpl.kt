package com.libro.librobackend.domain.readingrecord.repository

import com.libro.librobackend.domain.book.entity.QBook.book
import com.libro.librobackend.domain.readingrecord.entity.QReadingRecord.readingRecord
import com.libro.librobackend.domain.readingrecord.service.query.dto.ReadingRecordDto
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory

class ReadingRecordRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : ReadingRecordRepositoryCustom {
    override fun findAllWithBookInfoByUserId(userId: Long): List<ReadingRecordDto> {
        return jpaQueryFactory.select(
            Projections.constructor(
                ReadingRecordDto::class.java,
                readingRecord.id,
                book.title,
                book.author
            )
        )
            .from(readingRecord)
            .join(book).on(readingRecord.bookId.eq(book.id))
            .where(readingRecord.userId.eq(userId))
            .fetch()
    }
}