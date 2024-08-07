package com.greenify.dto.orderDtos;

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
public class ShippingDetailDto {
	
	private Long shippingId;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String state;
	
	private String country;
	
	private String city;
	
	private String mobileNumber;
	
	private String zipcode;
}
