package com.vinilcommerce.vinilcommerce.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import javax.persistence.*


@Entity
class ItemSale (
    @Id
    @GeneratedValue
    val id: Long,
    val price: BigDecimal = BigDecimal.ZERO,
    val cashbackValue: BigDecimal = BigDecimal.ZERO,
    val cashbackPercentage: BigDecimal? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    val product: Product,

    @JsonIgnore
    @ManyToOne
    val sale: Sale
)
