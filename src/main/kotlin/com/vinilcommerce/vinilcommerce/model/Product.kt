package com.vinilcommerce.vinilcommerce.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val artistName: String,
    @Enumerated(EnumType.STRING)
    val genre: Genre,
    val price: BigDecimal,
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at", updatable = true)
    val updatedAt: LocalDateTime? = null
)
