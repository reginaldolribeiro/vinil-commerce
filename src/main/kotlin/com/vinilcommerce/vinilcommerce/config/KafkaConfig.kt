package com.vinilcommerce.vinilcommerce.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.TopicBuilder


@Configuration
@EnableKafka
class KafkaConfig {

    @Value("\${spring.kafka.topic}")
    val topic: String = ""

    @Value("\${spring.kafka.partitions}")
    val partitions: Int = 1

    @Value("\${spring.kafka.replicas}")
    val replicas: Int = 1

    @Bean
    fun newTopic(): NewTopic {
        return TopicBuilder.name(topic)
            .partitions(partitions)
            .replicas(replicas)
            .build()
    }
}
