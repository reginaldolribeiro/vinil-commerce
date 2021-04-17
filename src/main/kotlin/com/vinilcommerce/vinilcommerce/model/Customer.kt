package com.vinilcommerce.vinilcommerce.model

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Customer(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val birthDate: LocalDate,
    val email: String
)
