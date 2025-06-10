package com.libro.librobackend.domain.readinggroup.entity

import com.libro.librobackend.domain.common.BaseTimeEntity
import jakarta.persistence.*

@Entity
class ReadingGroup(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val bookTitle: String,
    val bookAuthor: String,
    val description: String,
    @OneToMany(mappedBy = "readingGroup", cascade = [CascadeType.PERSIST])
    val sharedReadingRecords: MutableList<SharedReadingRecord> = mutableListOf()
) : BaseTimeEntity() {
    fun addReadingRecord(readingRecordId: Long) {
        sharedReadingRecords.add(
            SharedReadingRecord(
                readingGroup = this,
                readingRecordId = readingRecordId
            )
        )
    }
}