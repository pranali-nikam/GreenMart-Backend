package com.greenify.dto.productDtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductUpdateDto {

	private String fieldName;
	
	private String value;
	
}
