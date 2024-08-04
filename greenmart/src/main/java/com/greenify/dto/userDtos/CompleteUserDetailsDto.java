package com.greenify.dto.userDtos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CompleteUserDetailsDto {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private LocalDate dob;	
	
	private String mobileNumber;
	
	private Boolean isBlocked;
}
