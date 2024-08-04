package com.greenify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.sellerDtos.SellerDetailsDto;
import com.greenify.service.ProductService;
import com.greenify.service.SellerService;

@RestController
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/getSellers")
	@ResponseStatus(HttpStatus.OK)
	public List<SellerDetailsDto> getAllSellerDetails(){
		return sellerService.getSellerDetails();
	}
	
	@GetMapping("/productsCount")
	@ResponseStatus(HttpStatus.OK)
	public Long countAllProducts() {
		return productService.countAllProduct();
	}
}
