package com.vinilcommerce.vinilcommerce.repository

import com.vinilcommerce.vinilcommerce.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {

}
