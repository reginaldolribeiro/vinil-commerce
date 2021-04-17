package com.vinilcommerce.vinilcommerce.repository

import com.vinilcommerce.vinilcommerce.model.Genre
import com.vinilcommerce.vinilcommerce.model.Product
import org.springframework.data.repository.PagingAndSortingRepository


interface ProductRepository : PagingAndSortingRepository<Product, Long> {
    fun findByGenreOrderByName(genre: Genre?): MutableList<Product>
}
