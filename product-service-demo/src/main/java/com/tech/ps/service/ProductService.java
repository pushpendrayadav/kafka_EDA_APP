package com.tech.ps.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.tech.ps.config.KafkaProperties;
import com.tech.ps.core.ProductCreatedEvent;
import com.tech.ps.dto.Product;
import com.tech.ps.model.ProductEntity;
import com.tech.ps.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
	
	@Autowired
	KafkaProperties kafkaProperties;

	public List<Product> getAllProducts() {
		List<ProductEntity> list= productRepository.findAll();
		return list.stream().map(product-> new Product(product.getTitle(), product.getPrice(), product.getQuantity()))
				.toList();
	}

	public String createProduct(Product product) {
		ProductEntity productEntity=new ProductEntity();
		BeanUtils.copyProperties(product, productEntity);
		ProductEntity productDB=productRepository.save(productEntity);
		ProductCreatedEvent event=new ProductCreatedEvent(productDB.getId().toString(), productDB.getTitle(), productDB.getPrice(), productDB.getQuantity());
		// we have to publish this event in Kafka topic for that we need KAfka Producer i.e. KafkaTemplate
		
		CompletableFuture<SendResult<String, ProductCreatedEvent>> future=kafkaTemplate.send("products.created.event.topic", productDB.getId().toString(), event);
		
		future.whenCompleteAsync((reult,exception)->{
			if(exception!=null) {
				
				log.error("  *******  error occured in sending msg to kafka"+exception.getMessage());
				
			}else {
				log.info("  ********  msg sent to Kafka"+reult.getRecordMetadata());
			}
		});
		
		// if we want productId should be shared with client only after msg is sent to kafka then use join() method.
		future.join();
		
		log.info("  ********  returning product ID in Asynch");
		return productDB.getId().toString();
	}

	

}
