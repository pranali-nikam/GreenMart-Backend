package com.greenify.service;

import java.util.List;

import com.greenify.dto.userDtos.CompleteUserDetailsDto;
import com.greenify.dto.userDtos.UserDto;

public interface UserService {
	
	public UserDto registerUser(UserDto userDto);
	
	public List<CompleteUserDetailsDto> getUsers();
	
	public int blockUser(Long userId);
	
	public int unblockUser(Long userId);
}
