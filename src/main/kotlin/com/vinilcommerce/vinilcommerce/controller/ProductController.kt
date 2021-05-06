package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.Product
import com.vinilcommerce.vinilcommerce.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("album")
class ProductController(val productService: ProductService) {

    @GetMapping
    fun findAlbumsByGenre(@RequestParam(value = "genre", required = false) genre: String?) =
        ResponseEntity.ok(productService.findAlbumsByGenre(genre))

    @GetMapping("{id}")
    fun findAlbumById(@PathVariable id: Long) =
        ResponseEntity.ok(productService.findAlbumById(id))

    @PostMapping(produces = ["application/json"],consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid product: Product): Product {
        return productService.save(product)
    }

    @PutMapping("{id}")
    fun update(@RequestBody product: Product, @PathVariable id: Long) = productService.update(product, id)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = productService.delete(id)
}
