package com.greenify.dto;

import com.greenify.dto.sellerDtos.SellerDto;
import com.greenify.dto.userDtos.ProfileDto;
import com.greenify.dto.userDtos.UserDto;
import com.greenify.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginDto {

	private Long id;
	private UserDto userDto;
	private SellerDto sellerDto;
	private ProfileDto profileDto;

	private Role role;

	private boolean isBlocked;

}
