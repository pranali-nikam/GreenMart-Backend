package com.greenify.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class SellerDto {

	private UserDto user;
	
	private String storeName;
	
	private String address;
	
	private String phone;
	
	private String gstinNumber;
}
