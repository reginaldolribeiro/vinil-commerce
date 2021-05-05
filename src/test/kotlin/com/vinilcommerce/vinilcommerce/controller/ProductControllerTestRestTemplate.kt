package com.vinilcommerce.vinilcommerce.controller

import com.vinilcommerce.vinilcommerce.model.Genre
import com.vinilcommerce.vinilcommerce.model.Product
import com.vinilcommerce.vinilcommerce.repository.ProductRepository
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension::class)
class ProductControllerTestRestTemplate {

    @LocalServerPort
    var port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @MockBean
    lateinit var productRepository: ProductRepository

    private val expectedProduct = Product(
        10,
        "Teste",
        "Kreator",
        Genre.ROCK,
        BigDecimal(35),
        LocalDateTime.now(),
        null
    )

    @Test
    fun `#findAlbumById when finding album by a valid id it should returns a valid product`() {
        BDDMockito.`when`(productRepository.findById(10))
            .thenReturn(Optional.of(expectedProduct))

        val response = restTemplate.getForEntity("/album/10", Product::class.java)

        Assert.assertEquals(response.statusCodeValue, 200)
        Assert.assertEquals(response.body!!.name, expectedProduct.name)
        Assert.assertEquals(response.body!!.artistName, expectedProduct.artistName)
        Assert.assertEquals(response.body!!.genre, expectedProduct.genre)
        Assert.assertEquals(response.body!!.price, expectedProduct.price)
    }

}


// We need Webflux to run these tests
//@WebMvcTest(ProductController::class)
//class ProductControllerTest(
//    @Autowired val mockMvc: MockMvc
//) {
//    @MockBean lateinit var productService: ProductService
//    @MockBean lateinit var productRepository: ProductRepository
////    private lateinit var webTestClient: WebTestClient
//
//    private val webTestClient = MockMvcWebTestClient.bindTo(mockMvc).build()
//
////    @BeforeEach
////    fun setup(): Unit {
////        this.webTestClient = MockMvcWebTestClient.bindTo(mockMvc).build()
////    }
//
//    @Test
//    fun test1() {
//        val product = Product(
//            10,
//            "Teste",
//            "Kreator",
//            Genre.ROCK,
//            BigDecimal(35),
//            LocalDateTime.now(),
//            null
//        )
//        BDDMockito.`when`(productService.findAlbumById(10))
//            .thenReturn(product)
//
//        this.webTestClient.get().uri("/album/1").exchange().expectStatus().is2xxSuccessful.expectBody().isEmpty
//    }
//
//}