package com.vinilcommerce.vinilcommerce.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.vinilcommerce.vinilcommerce.model.Genre
import com.vinilcommerce.vinilcommerce.model.Product
import com.vinilcommerce.vinilcommerce.service.ProductService
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@WebMvcTest(ProductController::class)
class ProductControllerTest(@Autowired val objectMapper: ObjectMapper) {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var productService: ProductService

    private val pageableMock = PageRequest.of(0, 10)
    private val productMock =
        Product(10, "Test album", "Test artist", Genre.ROCK, BigDecimal(35), LocalDateTime.now(), null)

    @Test
    fun `#findAlbumById when a request with a valid ID it should returns a valid product`() {
        BDDMockito.`when`(productService.findAlbumById(productMock.id!!)).thenReturn(productMock)
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album/10")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(productMock.id))
            .andExpect(jsonPath("$.name").value(productMock.name))
            .andExpect(jsonPath("$.artist_name").value(productMock.artistName))
            .andExpect(jsonPath("$.genre").value(productMock.genre.toString()))
            .andExpect(jsonPath("$.price").value(productMock.price))

        Mockito.verify(productService, Mockito.times(1)).findAlbumById(productMock.id!!)
    }

    @Test
    fun `#findAlbumsByGenre when a request with an existing genre it should return products with that genre`() {
        val paginatedProductsMock = PageImpl(listOf(this.productMock, productMock))
        val genreRequest = productMock.genre.toString().toLowerCase()
        BDDMockito.`when`(productService.findAll(genreRequest, pageableMock)).thenReturn(paginatedProductsMock)
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album")
                .param("genre", "rock")
                .param("size", "10")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("content[0].id").value(productMock.id))
            .andExpect(jsonPath("content[0].name").value(productMock.name))
            .andExpect(jsonPath("content[0].artist_name").value(productMock.artistName))
            .andExpect(jsonPath("content[0].genre").value(productMock.genre.toString()))
            .andExpect(jsonPath("content[0].price").value(productMock.price))

        Mockito.verify(productService, Mockito.times(1)).findAll(genreRequest, pageableMock)
    }

    @Test
    fun `#findAlbumsByGenre when a request without genre it should return all of products`() {
        val paginatedProductsMock = PageImpl(listOf(this.productMock, productMock))
        BDDMockito.`when`(productService.findAll(null, pageableMock)).thenReturn(paginatedProductsMock)
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("content[0].id", notNullValue()))
            .andExpect(jsonPath("content[0].name", notNullValue()))
            .andExpect(jsonPath("content[0].artist_name", notNullValue()))
            .andExpect(jsonPath("content[0].price", notNullValue()))
            .andExpect(jsonPath("content[0].created_at", notNullValue()))
            .andExpect(jsonPath("content[1].id", notNullValue()))
            .andExpect(jsonPath("content[1].name", notNullValue()))
            .andExpect(jsonPath("content[1].artist_name", notNullValue()))
            .andExpect(jsonPath("content[1].price", notNullValue()))
            .andExpect(jsonPath("content[1].created_at", notNullValue()))

        Mockito.verify(productService, Mockito.times(1)).findAll(null, pageableMock)
    }

    @Test
    fun `#findAlbumsByGenre when a request with a non-existing genre it should return not found`() {
        val nonExistentGenre = "aaa"
        BDDMockito.`when`(productService.findAll(nonExistentGenre, pageableMock))
            .thenThrow(IllegalArgumentException::class.java)
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album")
                .param("genre", "aaa")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound)

        Mockito.verify(productService, Mockito.times(1)).findAll(nonExistentGenre, pageableMock)
    }

    @Test
    fun `#save when a request with a valid product it should be saved`() {
        val product = Product(null, "Test album", "Test artist", Genre.ROCK, BigDecimal(35))
        val productRequest = objectMapper.writer().writeValueAsString(product)

        BDDMockito.`when`(productService.save(product)).thenReturn(productMock)

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/album")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(productRequest)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name").value(productMock.name))
            .andExpect(jsonPath("$.artist_name").value(productMock.artistName))
            .andExpect(jsonPath("$.price").value(productMock.price))
            .andExpect(jsonPath("$.created_at", notNullValue()))
    }

    @Test
    fun `#update when a request with a valid product and ID it should update the product`() {
        val product = Product(10, "Test album updated", "Test artist updated", Genre.ROCK, BigDecimal(35))
        val productRequest = objectMapper.writer().writeValueAsString(product)

        BDDMockito.`when`(productService.update(product, productMock.id!!)).thenReturn(product)

        mockMvc.perform(
            MockMvcRequestBuilders
                .put("/album/{id}", productMock.id)
                .content(productRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(product.id))
            .andExpect(jsonPath("$.name").value(product.name))
            .andExpect(jsonPath("$.artist_name").value(product.artistName))
            .andExpect(jsonPath("$.price").value(product.price))
    }
}