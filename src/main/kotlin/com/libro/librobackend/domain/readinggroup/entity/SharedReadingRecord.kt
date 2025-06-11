package com.libro.librobackend.domain.readinggroup.entity

import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
class SharedReadingRecord(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val readingGroup: ReadingGroup,
    val readingRecordId: Long
) : BaseTimeEntity()