package com.vinilcommerce.vinilcommerce.model

import java.math.BigDecimal
import java.time.DayOfWeek
import javax.persistence.*

@Entity
data class Cashback (
    @Id
    @GeneratedValue
    val id: Long,
    @Enumerated
    val genre: Genre,
    @Enumerated
    val dayOfWeek: DayOfWeek,
    val value: BigDecimal
)