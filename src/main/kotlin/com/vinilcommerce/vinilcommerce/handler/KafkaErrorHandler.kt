package com.vinilcommerce.vinilcommerce.handler

import com.vinilcommerce.vinilcommerce.model.SaleRequest
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.internals.RecordHeader
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.KafkaListenerErrorHandler
import org.springframework.kafka.listener.ListenerExecutionFailedException
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import java.io.PrintWriter
import java.io.StringWriter

@Component(value = "kafkaErrorHandler")
class KafkaErrorHandler(val kafkaTemplate: KafkaTemplate<String, SaleRequest>) : KafkaListenerErrorHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${spring.kafka.topic}")
    val topic = ""

    override fun handleError(message: Message<*>, exception: ListenerExecutionFailedException): Unit? {
        val producerRecord = ProducerRecord<String, SaleRequest>(
            "$topic-error",
            (message.payload as ConsumerRecord<*, *>).value() as SaleRequest
        )
        val sw = StringWriter()
        exception.printStackTrace(PrintWriter(sw))
        val stackTrace = sw.toString()
        logger.error("Exception: $stackTrace")
        producerRecord.headers().add(RecordHeader("x-exception", stackTrace.toByteArray()))
        kafkaTemplate.send(producerRecord)
        return null
    }
}
