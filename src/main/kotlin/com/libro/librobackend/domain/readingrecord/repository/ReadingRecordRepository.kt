package com.libro.librobackend.domain.readingrecord.repository

import com.libro.librobackend.domain.readingrecord.entity.ReadingRecord
import org.springframework.data.jpa.repository.JpaRepository

interface ReadingRecordRepository : JpaRepository<ReadingRecord, Long>, ReadingRecordRepositoryCustom