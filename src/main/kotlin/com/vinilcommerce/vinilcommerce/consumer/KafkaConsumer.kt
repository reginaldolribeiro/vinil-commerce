package com.vinilcommerce.vinilcommerce.consumer

import com.vinilcommerce.vinilcommerce.model.SaleRequest
import com.vinilcommerce.vinilcommerce.service.SaleService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(val saleService: SaleService) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(
        topics = ["\${spring.kafka.topic}"],
        groupId = "\${spring.kafka.group-id}",
        errorHandler = "kafkaErrorHandler"
    )
    fun onMessage(message: ConsumerRecord<String, SaleRequest>) {
        logger.info("message consumed - sale request: ${message.value()}")
        saleService.sale(message.value())
    }
}