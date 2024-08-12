package com.greenify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.productDtos.ProductDto;
import com.greenify.service.ProductService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/add/{sellerId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto addProduct(@RequestBody ProductDto productDto,@PathVariable Long sellerId) {
		return productService.addProduct(productDto,sellerId);
	}
	
	@DeleteMapping("/delete/{productId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable Long productId) {
	 productService.deleteProducts(productId);
	}
	
}
