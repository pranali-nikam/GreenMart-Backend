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
public class ProductSearchDto {
    private String productName;
	
	private String description;
	
	private BigDecimal price;
	
	private String imageUrl;
}
