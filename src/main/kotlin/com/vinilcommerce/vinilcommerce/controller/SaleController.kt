package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.Sale
import com.vinilcommerce.vinilcommerce.model.SaleRequest
import com.vinilcommerce.vinilcommerce.service.SaleService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("sale")
class SaleController(val saleService: SaleService) {

    @PostMapping
    fun sale(@RequestBody sale: SaleRequest): ResponseEntity<Unit> {
        saleService.publishSaleMessage(sale)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = saleService.findById(id)

    @GetMapping
    fun findSalesByDate(
        @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") start: LocalDate? = null,
        @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") end: LocalDate? = null
    ): ResponseEntity<MutableList<Sale>> {
        val sales = saleService.findByRangeDate(start, end)
        return ResponseEntity.ok(sales)
    }
}