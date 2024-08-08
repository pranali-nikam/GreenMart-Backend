package com.greenify.dto;

import javax.validation.constraints.NotNull;

public class WishlistDto{
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long productId;
	
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

}
