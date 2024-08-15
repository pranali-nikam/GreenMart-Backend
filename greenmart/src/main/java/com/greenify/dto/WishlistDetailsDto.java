package com.greenify.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WishlistDetailsDto {

	private String productName;
	
	private String description;
	
	private BigDecimal price;
	
	private int stock;

	private String categoryName;
	
	private String imageUrl;
	
	private Long productId; 
}
