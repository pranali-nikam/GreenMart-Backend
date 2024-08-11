package com.greenify.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.CartDto;
import com.greenify.service.CartService;

@RestController
@RequestMapping("/cart")

public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProductToCart(@RequestBody @Valid CartDto cartDto){
		cartService.addProductToCart(cartDto);
		return ResponseEntity.status(201).body("Product added to cart successfully");
	}
    
	@GetMapping("/{userId}/products")
	public ResponseEntity<?> getProductsInCart(@PathVariable @NotNull Long userId){
		return ResponseEntity.ok(cartService.getProductsInCart(userId));
   }
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProductQuantity(@RequestBody @Valid CartDto cartDto ){
		cartService.updateProductQuantity(cartDto);
		return ResponseEntity.ok("Product quantity update successfully");
		
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> removeProductFromCart(@RequestBody @Valid CartDto cartDto){
		cartService.removeProductFromCart(cartDto);
		return ResponseEntity.ok("Product removed from cart sucsessfully");
 }
}