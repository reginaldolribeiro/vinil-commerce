package com.vinilcommerce.vinilcommerce.producer

import com.vinilcommerce.vinilcommerce.model.SaleRequest
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(val kafkaTemplate: KafkaTemplate<String, SaleRequest>) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${spring.kafka.topic}")
    val topic: String = ""

    fun publishMessage(saleRequest: SaleRequest) {
        logger.info("message published - saleRequest: ${saleRequest}")
        val producerRecord = ProducerRecord(topic, "sale", saleRequest)
        kafkaTemplate.send(producerRecord)
    }
}
