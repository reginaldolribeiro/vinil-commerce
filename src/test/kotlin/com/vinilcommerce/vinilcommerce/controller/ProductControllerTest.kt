package com.vinilcommerce.vinilcommerce.controller

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
class ProductControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var productService: ProductService

    private val productMock =
        Product(10, "Test album", "Test artist", Genre.ROCK, BigDecimal(35), LocalDateTime.now(), null)

    @Test
    fun `#findAlbumById when a request with a valid ID it should returns a valid product`() {
        BDDMockito.`when`(productService.findAlbumById(productMock.id)).thenReturn(productMock)
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

        Mockito.verify(productService, Mockito.times(1)).findAlbumById(productMock.id)
    }

    @Test
    fun `#findAlbumsByGenre when a request with an existing genre it should return products with that genre`() {
        val genreRequest = productMock.genre.toString().toLowerCase()
        BDDMockito.`when`(productService.findAlbumsByGenre(genreRequest)).thenReturn(listOf(productMock))
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album?genre=$genreRequest")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("[0].id").value(productMock.id))
            .andExpect(jsonPath("[0].name").value(productMock.name))
            .andExpect(jsonPath("[0].artist_name").value(productMock.artistName))
            .andExpect(jsonPath("[0].genre").value(productMock.genre.toString()))
            .andExpect(jsonPath("[0].price").value(productMock.price))

        Mockito.verify(productService, Mockito.times(1)).findAlbumsByGenre(genreRequest)
    }

    @Test
    fun `#findAlbumsByGenre when a request without genre it should return all of products`() {
        BDDMockito.`when`(productService.findAlbumsByGenre(null)).thenReturn(listOf(productMock, productMock))
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id", notNullValue()))
            .andExpect(jsonPath("$[0].name", notNullValue()))
            .andExpect(jsonPath("$[0].artist_name", notNullValue()))
            .andExpect(jsonPath("$[0].price", notNullValue()))
            .andExpect(jsonPath("$[0].created_at", notNullValue()))
            .andExpect(jsonPath("$[1].id", notNullValue()))
            .andExpect(jsonPath("$[1].name", notNullValue()))
            .andExpect(jsonPath("$[1].artist_name", notNullValue()))
            .andExpect(jsonPath("$[1].price", notNullValue()))
            .andExpect(jsonPath("$[1].created_at", notNullValue()))

        Mockito.verify(productService, Mockito.times(1)).findAlbumsByGenre(null)
    }

    @Test
    fun `#findAlbumsByGenre when a request with a non-existing genre it should return not found`() {
        val nonExistentGenre = "aaa"
        BDDMockito.`when`(productService.findAlbumsByGenre(nonExistentGenre))
            .thenThrow(IllegalArgumentException::class.java)
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/album?genre=$nonExistentGenre")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound)

        Mockito.verify(productService, Mockito.times(1)).findAlbumsByGenre(nonExistentGenre)
    }
}