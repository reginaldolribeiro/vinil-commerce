package com.vinilcommerce.vinilcommerce.service

import com.vinilcommerce.vinilcommerce.exception.NotFoundException
import com.vinilcommerce.vinilcommerce.model.Customer
import com.vinilcommerce.vinilcommerce.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun findById(customerId: Long) =
        customerRepository.findById(customerId).orElseThrow { NotFoundException("Customer not found!") }

    fun findAll(): MutableList<Customer> = customerRepository.findAll()

    fun save(customer: Customer) = customerRepository.save(customer)

    fun update(customer: Customer, id: Long): Customer {
        val customerToBeSaved = findById(id).copy(
            name = customer.name,
            email = customer.email,
            birthDate = customer.birthDate,
            updatedAt = LocalDateTime.now()
        )
        return customerRepository.save(customerToBeSaved)
    }
}
