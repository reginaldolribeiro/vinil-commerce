package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.SaleRequest
import com.vinilcommerce.vinilcommerce.service.SaleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("sale")
class SaleController(val saleService: SaleService) {

    @PostMapping
    fun sale(@RequestBody sale: SaleRequest): ResponseEntity<Unit> {
        saleService.publishSaleMessage(sale)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping
    fun findAll() = saleService.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = saleService.findById(id)
}