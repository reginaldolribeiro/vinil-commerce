package com.vinilcommerce.vinilcommerce.model

import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Cashback (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Enumerated(EnumType.STRING)
    val genre: Genre,
    @Enumerated(EnumType.STRING)
    val dayOfWeek: DayOfWeek,
    val value: BigDecimal,
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at", updatable = true)
    val updatedAt: LocalDateTime? = null
)