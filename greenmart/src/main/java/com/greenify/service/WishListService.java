package com.greenify.service;

import java.util.List;

import com.greenify.dto.WishlistDto;

public interface WishListService {
	
	List<WishlistDto> getWishlistByUserId(Long userId);
	
	void addProductToWishlist(WishlistDto wishlistDto);
	
	void removeProductFromWishlist(WishlistDto wishlistDto);

}
