package com.vinilcommerce.vinilcommerce.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Sale(
    @Id
    @GeneratedValue
    val id: Long,
    @OneToOne
    val customer: Customer,
    val totalValue: BigDecimal = BigDecimal.ZERO,
    val totalCashback: BigDecimal = BigDecimal.ZERO,
    @OneToMany(mappedBy = "sale", cascade = [CascadeType.ALL])
    val itens: List<ItemSale> = ArrayList<ItemSale>()
)
