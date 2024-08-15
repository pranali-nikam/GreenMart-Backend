package com.greenify.dto.productDtos;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class ProductQuantityDto {
	
	@NotNull
	private Long productId;
	@NotNull
	private String productName;
	
	@NotNull
	private int quantity;
	
	private String imageUrl; 
	
	private String stock;
	
	private BigDecimal price;
	
	private Long sellerId; 
}
