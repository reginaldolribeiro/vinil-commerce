package com.vinilcommerce.vinilcommerce.service

import com.vinilcommerce.vinilcommerce.exception.NotFoundException
import com.vinilcommerce.vinilcommerce.model.Genre
import com.vinilcommerce.vinilcommerce.model.Product
import com.vinilcommerce.vinilcommerce.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(val productRepository: ProductRepository) {

//    @Value("\${spring.chafundi}")
//    val chafundi: String = ""

    fun findAlbumsByGenre(genre: String?): MutableIterable<Product> {
//        println("******** chafundi: $chafundi ************")
        return if (genre.isNullOrBlank()) {
            productRepository.findAll()
        } else {
            try {
                val genreEnum = genre.let { Genre.valueOf(genre.toUpperCase()) }
                return productRepository.findByGenreOrderByName(genreEnum)
            } catch (e: Exception) {
                throw IllegalArgumentException("Genre not found!")
            }
        }
    }

    fun findAlbumById(id: Long) =
        productRepository.findById(id)
            .orElseThrow { NotFoundException("Product not found!") }

    fun save(product: Product): Product {
        println("************* Entrou no SERVICE!!!! ************************")
        return productRepository.save(product)
    }

    fun update(product: Product, id: Long): Product {
        val productToBeSaved = findAlbumById(id)
            .copy(
                name = product.name,
                artistName = product.artistName,
                genre = product.genre,
                price = product.price,
                updatedAt = LocalDateTime.now()
            )
        return productRepository.save(productToBeSaved)
    }

    fun delete(id: Long) {
        if(!productRepository.existsById(id)){
            throw NotFoundException("Product not found!")
        }
        productRepository.deleteById(id)
    }
}
