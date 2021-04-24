package com.vinilcommerce.vinilcommerce.model

import com.fasterxml.jackson.annotation.JsonBackReference
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class ItemSale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val cashbackValue: BigDecimal = BigDecimal.ZERO,
    val cashbackPercentage: BigDecimal = BigDecimal.ZERO,

    @OneToOne(cascade = [CascadeType.ALL])
    val product: Product,

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sale_id")
    var sale: Sale? = null,
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at", updatable = true)
    val updatedAt: LocalDateTime? = null
)
