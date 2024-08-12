package com.greenify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.categoryDtos.CategoryNameDto;
import com.greenify.dto.productDtos.ProductDto;
import com.greenify.dto.productDtos.ProductSearchDto;
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
	
	@GetMapping("/category/{categoryId}")
	@ResponseStatus(HttpStatus.OK)
	List<ProductSearchDto> getAllProductByCategoryId(@PathVariable Long categoryId){
		return productService.getAllProductByCategoryId(categoryId);
	}
	
	@GetMapping("/products/{productName}")
	@ResponseStatus(HttpStatus.OK)
	List<ProductSearchDto> getProductByProductName(@PathVariable String productName){
		return productService.getProductByProductName(productName);
	}
	
	
	
}
