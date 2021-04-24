package com.vinilcommerce.vinilcommerce.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val birthDate: LocalDate,
    val email: String,
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at", updatable = true)
    val updatedAt: LocalDateTime? = null
)
