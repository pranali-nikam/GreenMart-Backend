package com.greenify.service;

import java.util.List;

import com.greenify.dto.LoginDto;
import com.greenify.dto.SignInRequest;
import com.greenify.dto.orderDtos.ShippingDetailDto;
import com.greenify.dto.userDtos.CompleteUserDetailsDto;
import com.greenify.dto.userDtos.ProfileDto;
import com.greenify.dto.userDtos.UserDto;

public interface UserService {
	
	public UserDto registerUser(UserDto userDto);
	
	public List<CompleteUserDetailsDto> getUsers();
	
	public int blockUnblockUser(Long userId,Boolean isBlocked);
		
	public ShippingDetailDto addShippingAddress(ShippingDetailDto shippingDetailDto,Long userId);
	
	public List<ShippingDetailDto> getShippingAddress(Long userId);
	
	public ProfileDto getUserProfile(Long userId);
	
	public LoginDto authenticateUser(SignInRequest request);



}
