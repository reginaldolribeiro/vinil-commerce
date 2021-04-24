package com.vinilcommerce.vinilcommerce.repository

import com.vinilcommerce.vinilcommerce.model.Sale
import org.springframework.data.jpa.repository.JpaRepository

interface SaleRepository : JpaRepository<Sale, Long> {
}