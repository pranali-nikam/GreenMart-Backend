package com.greenify.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenify.dto.WishlistDetailsDto;
import com.greenify.dto.WishlistDto;
import com.greenify.exceptions.BusinessException;
import com.greenify.service.WishListService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/wishlist")
@Validated
public class WishlistController {

	@Autowired
	private WishListService wishlistService;

	@GetMapping("/getWishlist/{userId}")
	public ResponseEntity<?> getWishlistByUserId(@PathVariable @NotNull Long userId) {
		List<WishlistDetailsDto> wishlist = wishlistService.getWishlistByUserId(userId);
		if (wishlist.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(wishlist);
	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<?> addProductToWishlist(@RequestBody @Valid WishlistDto wishlistDto,
			@PathVariable Long userId) {
		String message = wishlistService.addProductToWishlist(wishlistDto, userId);
		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/removeFromWishlist/{userId}/{productId}")
	public ResponseEntity<?> removeProductFromWishlist(
			@PathVariable("userId") Long userId,@PathVariable("productId") Long productId) {

		String message = wishlistService.removeProductFromWishlist(productId, userId);
		return ResponseEntity.ok(message);

	}

}