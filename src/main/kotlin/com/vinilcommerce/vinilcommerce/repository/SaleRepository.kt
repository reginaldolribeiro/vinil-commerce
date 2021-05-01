package com.vinilcommerce.vinilcommerce.repository

import com.vinilcommerce.vinilcommerce.model.Sale
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface SaleRepository : JpaRepository<Sale, Long> {

    fun findAllBySaleDateBetweenOrderBySaleDateDesc(
        initialDate: LocalDateTime,
        finalDate: LocalDateTime
    ): MutableList<Sale>
}