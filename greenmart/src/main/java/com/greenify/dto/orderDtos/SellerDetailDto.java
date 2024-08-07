package com.greenify.dto.orderDtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class SellerDetailDto {

	private Long sellerId;
	
	private String storeName;
}
