package com.greenify.dto.productDtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductPartialUpdateDto {

	private String productName;
	
	private String description;
	
	private BigDecimal price;
	
	private int stock;
	
	private String imageUrl;
}
