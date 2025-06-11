package com.libro.librobackend.domain.readinggroup.repository

import com.libro.librobackend.domain.readinggroup.entity.ReadingGroup
import org.springframework.data.jpa.repository.JpaRepository

interface ReadingGroupRepository : JpaRepository<ReadingGroup, Long>, ReadingGroupRepositoryCustom