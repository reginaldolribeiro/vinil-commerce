package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.Customer
import com.vinilcommerce.vinilcommerce.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController(val customerService: CustomerService) {

    @PostMapping
    fun save(@RequestBody customer: Customer) = customerService.save(customer)

    @GetMapping
    fun findAll() = customerService.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity.ok(customerService.findById(id))

    @PutMapping("{id}")
    fun update(@RequestBody customer: Customer, @PathVariable id: Long) = customerService.update(customer, id)
}