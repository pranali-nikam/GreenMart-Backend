package com.greenify.dto.userDtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserDetailsDto {

	private String name;
		
	private String email;
		
	private String mobileNumber;
}
