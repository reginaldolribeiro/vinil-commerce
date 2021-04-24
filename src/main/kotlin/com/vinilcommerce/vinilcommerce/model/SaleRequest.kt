package com.vinilcommerce.vinilcommerce.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SaleRequest (
    @JsonProperty("customer_id") val customerId: Long,
    val products: List<Long> = emptyList()
)