package com.vinilcommerce.vinilcommerce.model

import com.fasterxml.jackson.annotation.JsonBackReference
import java.math.BigDecimal
import javax.persistence.*


@Entity
data class ItemSale (
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
    var sale: Sale? = null
)
