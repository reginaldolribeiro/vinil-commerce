package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.SaleRequest
import com.vinilcommerce.vinilcommerce.service.SaleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("sale")
class SaleController(val saleService: SaleService) {

    @PostMapping
    fun sale(@RequestBody sale: SaleRequest): ResponseEntity<Unit> {
        saleService.publishSaleMessage(sale)
        return ResponseEntity(HttpStatus.CREATED)
    }
}