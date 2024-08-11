package com.greenify.dto;

import com.sun.istack.NotNull;

public class CartDto {
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long productId;
	
	@NotNull
	private int quantity;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	

}
