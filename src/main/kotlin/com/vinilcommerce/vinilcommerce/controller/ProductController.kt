package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.Product
import com.vinilcommerce.vinilcommerce.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("album")
class ProductController(val productService: ProductService) {

    @GetMapping
    fun findAlbumsByGenre(@RequestParam(value = "genre", required = false) genre: String?): MutableIterable<Product> {
        return productService.findAlbumsByGenre(genre)
    }

    @PostMapping
    fun save(@RequestBody product: Product) = productService.save(product)

    @GetMapping("{id}")
    fun findAlbumById(@PathVariable id: Long) =
        ResponseEntity.ok(productService.findAlbumById(id))

    @PutMapping("{id}")
    fun update(@RequestBody product: Product, @PathVariable id: Long) = productService.update(product, id)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = productService.delete(id)
}
