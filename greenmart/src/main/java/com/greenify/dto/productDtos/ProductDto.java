package com.greenify.dto.productDtos;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductDto {

	private String productName;
	
	private String description;
	
	private BigDecimal price;
	
	private int stock;

	private Long categoryId;
	
	private MultipartFile imageUrl;

}
