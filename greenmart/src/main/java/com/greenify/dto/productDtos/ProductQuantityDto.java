package com.greenify.dto.productDtos;

import javax.validation.constraints.NotNull;

public class ProductQuantityDto {
	
	@NotNull
	private Long productId;
	@NotNull
	private String productName;
	@NotNull
	private int quantity;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
