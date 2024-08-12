package com.greenify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.productDtos.ProductDetailsDto;
import com.greenify.dto.sellerDtos.SellerDetailsDto;
import com.greenify.dto.userDtos.CompleteUserDetailsDto;
import com.greenify.service.ProductService;
import com.greenify.service.SellerService;
import com.greenify.service.UserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/admin")
public class AdminController {

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
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
	
	
	@GetMapping("/getAllProducts")
	@ResponseStatus(HttpStatus.OK)
	List<ProductDetailsDto> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/getUsers")
	@ResponseStatus(HttpStatus.OK)
	List<CompleteUserDetailsDto> getUsers(){
		return userService.getUsers();
	}
	
	@PatchMapping("/blockUser/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public int blockUser(@PathVariable Long userId) {
		return userService.blockUser(userId);
	}
	
	
	@PatchMapping("/unblockUser/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public int unblockUser(@PathVariable Long userId) {
		return userService.unblockUser(userId);
	}
	
	
	@PatchMapping("/blockSeller/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public int blockSeller(@PathVariable Long sellerId) {
		return sellerService.blockSeller(sellerId);
	}
	
	
	@PatchMapping("/unblockSeller/{sellerId}")
	@ResponseStatus(HttpStatus.OK)
	public int unblockSeller(@PathVariable Long sellerId) {
		return sellerService.unblockSeller(sellerId);
	}
	
	
}
