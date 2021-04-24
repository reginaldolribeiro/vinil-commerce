package com.vinilcommerce.vinilcommerce.service

import com.vinilcommerce.vinilcommerce.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun findById(customerId: Long) =
        customerRepository.findById(customerId)
}
