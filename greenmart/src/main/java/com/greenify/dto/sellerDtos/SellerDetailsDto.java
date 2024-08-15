package com.greenify.dto.sellerDtos;

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
public class SellerDetailsDto {
	
	private Long sellerId;
	
	private String name;
	
	private String email;
		
	private String mobileNumber;
	
	private String storeName;
	
	private String address;
	
	private String phone;
	
	private String gstinNumber;
	
	private Boolean isBlocked;
}
