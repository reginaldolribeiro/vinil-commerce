package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.Genre
import com.vinilcommerce.vinilcommerce.model.Product
import com.vinilcommerce.vinilcommerce.service.ProductService
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@WebMvcTest(ProductController::class)
class ProductControllerTestMockMvc {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var productService: ProductService

    @Test
    fun `#findAlbumById`() {
        val product = Product(
            10,
            "Teste",
            "Kreator",
            Genre.ROCK,
            BigDecimal(35),
            LocalDateTime.now(),
            null
        )

        BDDMockito.`when`(productService.findAlbumById(product.id)).thenReturn(product)

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album/10")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
    }
}