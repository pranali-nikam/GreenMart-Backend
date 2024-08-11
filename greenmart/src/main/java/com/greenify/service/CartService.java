package com.greenify.service;

import java.util.List;

import com.greenify.dto.CartDto;
import com.greenify.dto.productDtos.ProductQuantityDto;

public interface CartService {
	
	void addProductToCart(CartDto cartDto);
	List<ProductQuantityDto> getProductsInCart(Long userId);
	void updateProductQuantity(CartDto cartDto);
	void removeProductFromCart(CartDto cartDto);

}
