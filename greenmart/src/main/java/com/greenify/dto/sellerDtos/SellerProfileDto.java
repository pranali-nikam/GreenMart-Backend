package com.greenify.dto.sellerDtos;

import java.time.LocalDate;

import com.greenify.dto.userDtos.ProfileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerProfileDto {

    private String name;
	
	private String mobileNumber;
	
	private String email;
	
	private LocalDate dob;
	
	
    private String storeName;
	
	private String address;
	
	private String phone;
	
	private String gstinNumber;
}
