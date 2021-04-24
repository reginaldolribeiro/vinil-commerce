package com.vinilcommerce.vinilcommerce.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Sale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne
    val customer: Customer,
    var totalValue: BigDecimal = BigDecimal.ZERO,
    var totalCashback: BigDecimal = BigDecimal.ZERO,

    @JsonManagedReference
    @OneToMany(mappedBy = "sale", cascade = [CascadeType.ALL])
    var items: List<ItemSale> = emptyList(),
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at", updatable = true)
    val updatedAt: LocalDateTime? = null
) {
    fun calculateTotalCashback() = items.map { item -> item.cashbackValue }.reduce { acc, next -> acc.plus(next) }
    fun calculateTotalValue() = items.map { item -> item.price }.reduce { acc, next -> acc.plus(next) }
    fun calculate() {
        items.forEach { item ->
            this.totalCashback = this.totalCashback.add(item.cashbackValue)
            this.totalValue = this.totalValue.add(item.price.plus())
        }
    }
}
