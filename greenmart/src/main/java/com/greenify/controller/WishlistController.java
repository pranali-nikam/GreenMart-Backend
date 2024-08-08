package com.greenify.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.WishlistDto;
import com.greenify.exceptions.BusinessException;
import com.greenify.service.WishListService;

@RestController
@RequestMapping("/wishlist")
@Validated

public class WishlistController {

	@Autowired
	private WishListService wishlistService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getWishlistByUserId(@PathVariable @NotNull Long userId){
		List<WishlistDto> wishlist = wishlistService.getWishlistByUserId(userId);
		if(wishlist.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(wishlist);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addProductToWishlist(@RequestBody @Valid WishlistDto wishlistDto){
		try {
			wishlistService.addProductToWishlist(wishlistDto);
			return ResponseEntity.status(201).build();
		}catch(BusinessException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> removeProductFromWishlist(@RequestBody @Valid WishlistDto wishlistDto){
		try {
			wishlistService.removeProductFromWishlist(wishlistDto);
			return ResponseEntity.noContent().build();
		}catch (BusinessException e) {
			return ResponseEntity.notFound().build();
		}
		
	
	
}
	}