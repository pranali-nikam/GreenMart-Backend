package com.greenify.dto.productDtos;

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
public class ProductSearchDto {
	
	private Long productId;
	
    private String productName;
	
	private String description;
	
	private BigDecimal price;
	
	private String imageUrl;
}
