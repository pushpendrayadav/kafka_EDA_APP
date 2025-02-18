package com.tech.ps.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Bean
	NewTopic createNewTopic() {
		return TopicBuilder.name("products.created.event.topic")
				.partitions(1)
				.replicas(1)
				//.configs(Map.of("min.insynch.replicas","2"))
				.build();
	}

}
