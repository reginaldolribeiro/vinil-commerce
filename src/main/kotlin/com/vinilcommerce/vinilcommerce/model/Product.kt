package com.vinilcommerce.vinilcommerce.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val artistName: String,
    @Enumerated(EnumType.STRING)
    val genre: Genre,
    val price: BigDecimal
)
