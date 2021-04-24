package com.vinilcommerce.vinilcommerce.service

import com.vinilcommerce.vinilcommerce.exception.NotFoundException
import com.vinilcommerce.vinilcommerce.model.Genre
import com.vinilcommerce.vinilcommerce.model.ItemSale
import com.vinilcommerce.vinilcommerce.model.Sale
import com.vinilcommerce.vinilcommerce.model.SaleRequest
import com.vinilcommerce.vinilcommerce.producer.KafkaProducer
import com.vinilcommerce.vinilcommerce.repository.SaleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate

@Service
class SaleService(
    private val saleRepository: SaleRepository,
    private val kafkaProducer: KafkaProducer,
    private val customerService: CustomerService,
    private val productService: ProductService,
    private val cashbackService: CashbackService
) {

    fun publishSaleMessage(saleRequest: SaleRequest) {
        kafkaProducer.publishMessage(saleRequest)
    }

    @Transactional
    fun sale(saleRequest: SaleRequest) {
        val customer = customerService.findById(saleRequest.customerId)
            .orElseThrow { NotFoundException("Customer not found!") }
        val sale = Sale(customer = customer)
        sale.items = buildItemSale(saleRequest.products, sale)
        sale.calculate()
        saleRepository.save(sale)
    }

    private fun buildItemSale(products: List<Long>, sale: Sale): List<ItemSale> {
        return products.map { productId ->
            val product = productService.findAlbumById(productId)
            val cashbackPercentage = getCashbackPercentage(product.genre)
            val calculatedPrice = getCalculatedPrice(product.price, cashbackPercentage)
            val calculatedCashbackValue = calculatedCashbackValue(product.price, calculatedPrice)
            ItemSale(
                price = calculatedPrice,
                cashbackPercentage = cashbackPercentage,
                cashbackValue = calculatedCashbackValue,
                product = product,
                sale = sale
            )
        }
    }

    private fun getCashbackPercentage(genre: Genre) =
        cashbackService.getCashback(genre, LocalDate.now().dayOfWeek).value

    private fun getCalculatedPrice(price: BigDecimal, cashbackPercentage: BigDecimal): BigDecimal {
        val discount = BigDecimal.ONE.subtract(cashbackPercentage.divide(BigDecimal(100)))
        return price.multiply(discount)
    }

    private fun calculatedCashbackValue(originalPrice: BigDecimal, calculatedPrice: BigDecimal): BigDecimal =
        originalPrice.subtract(calculatedPrice)

}