package com.greenify.service;

import java.util.List;

import com.greenify.dto.WishlistDetailsDto;
import com.greenify.dto.WishlistDto;

public interface WishListService {
	
	List<WishlistDetailsDto> getWishlistByUserId(Long userId);
	
	void addProductToWishlist(WishlistDto wishlistDto,Long userId);
	
	void removeProductFromWishlist(WishlistDto wishlistDto,Long userId);

}
