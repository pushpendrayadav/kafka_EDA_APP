package com.tech.ps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.ps.dto.Product;
import com.tech.ps.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@GetMapping
	@RequestMapping("/fetchAll")
	public ResponseEntity<List<Product>> getAllProducts(){
		log.info("  ********  fetchAll api triggered");
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
				
	}
	
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<String> createProduct(@RequestBody Product product){
		log.info("  ********  create api triggered");
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
				
	}
	
	

}
